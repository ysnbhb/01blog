package _blog.com._blog.controllers;

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
    public void like(@RequestParam(defaultValue = "0", name = "postId") long post_id, HttpServletRequest request)
            throws UserExeption {
        if (post_id == 0) {
            return;
        }
        User user = (User) request.getAttribute("user");
        reactionServer.like(post_id, user);
    }
}
