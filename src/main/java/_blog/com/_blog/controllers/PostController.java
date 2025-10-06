package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.dto.PostConvert;
import _blog.com._blog.services.PostServ;
import _blog.com._blog.utils.PostReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PostController {
    PostServ postRepositery;

    public PostController(PostServ postRepositery) {
        this.postRepositery = postRepositery;
    }

    @PostMapping(path = "creat_post")
    public ResponseEntity<?> post(@Valid @ModelAttribute PostReq postReq, HttpServletRequest request) throws Exception {
        Long userid = (long) request.getAttribute("userId");
        Post post = postRepositery.save(postReq, userid);
        return ResponseEntity.ok(PostConvert.convertToPostReq(post));
    }

    @PostMapping(path = "delete_post")
    public ResponseEntity<?> delete(@RequestParam("id") String id, HttpServletRequest request) throws Exception {
        Long userid = (long) request.getAttribute("userId");
        postRepositery.delete(Long.parseLong(id), userid);
        return null;
    }

}
