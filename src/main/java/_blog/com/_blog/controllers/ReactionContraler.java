package _blog.com._blog.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.services.ReactionServer;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api")
public class ReactionContraler {
    private final ReactionServer reactionServer;

    public ReactionContraler(ReactionServer reactionServer) {
        this.reactionServer = reactionServer;

    }

    @PostMapping("like")
    public Map<String, Object> like(@RequestParam(defaultValue = "0", name = "postId") long post_id,
            HttpServletRequest request)
            throws UserExeption {
        User user = (User) request.getAttribute("user");
        return reactionServer.like(post_id, user);
    }
}
