package _blog.com._blog.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;
import _blog.com._blog.dto.CommentConert;
import _blog.com._blog.services.CommentsServ;
import _blog.com._blog.utils.CommentReq;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class Comments {
    private final CommentsServ commentsServ;

    public Comments(CommentsServ commentsServ) {
        this.commentsServ = commentsServ;
    }

    @PostMapping("creat_comment")
    public CommentReq save(@Valid @RequestBody CommentReq commentReq, @RequestAttribute("user") User user)
            throws Exception {
        return CommentConert.convertToPost(commentsServ.save(commentReq, user));
    }
}
