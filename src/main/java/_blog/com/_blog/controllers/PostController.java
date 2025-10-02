package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.utils.PostReq;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PostController {
    PostRepositery postRepositery;

    public PostController(PostRepositery postRepositery) {
        this.postRepositery = postRepositery;
    }

    @PostMapping(path = "creat_post")
    public ResponseEntity<?> post(@Valid @ModelAttribute PostReq postReq) {
        System.out.println(postReq.toString());
        return null;
    }

}
