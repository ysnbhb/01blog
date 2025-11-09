package _blog.com._blog.middleware;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import _blog.com._blog.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private static final int MAX_REQUESTS = 100;
    private static final int WINDOW_SECONDS = 60;

    private final Map<String, RequestWindow> requestMap = new ConcurrentHashMap<>();

    private static class RequestWindow {
        long startTime;
        int count;

        RequestWindow(long startTime) {
            this.startTime = startTime;
            this.count = 0;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String key = getKey(request);
        long now = Instant.now().getEpochSecond();

        RequestWindow window = requestMap.computeIfAbsent(key, k -> new RequestWindow(now));
        if (now - window.startTime >= WINDOW_SECONDS) {
            window.startTime = now;
            window.count = 0;
        }
        window.count++;
        if (window.count > MAX_REQUESTS) {
            response.setStatus(429);
            response.getWriter().write("⏳ Too many requests, please slow down.");
            return;
        }
        response.setHeader("X-RateLimit-Limit", String.valueOf(MAX_REQUESTS));
        response.setHeader("X-RateLimit-Remaining", String.valueOf(MAX_REQUESTS - window.count));

        filterChain.doFilter(request, response);
    }

    private String getKey(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Long userId = jwtService.extractUserId(token);
            return "user:" + userId;
        }
        String forwarded = request.getHeader("X-Forwarded-For");
        return (forwarded != null ? forwarded.split(",")[0].trim() : request.getRemoteAddr());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/image/") || path.startsWith("/video/") || path.startsWith("/uploads/");
    }
}
