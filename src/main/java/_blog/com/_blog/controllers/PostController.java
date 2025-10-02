package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.services.PostServ;
import _blog.com._blog.utils.PostReq;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PostController {
    PostServ postRepositery;

    public PostController(PostServ postRepositery) {
        this.postRepositery = postRepositery;
    }

    @PostMapping(path = "creat_post")
    public ResponseEntity<?> post(@Valid @ModelAttribute PostReq postReq) {
        try {
            postRepositery.save(postReq);
            System.out.println(postReq.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
