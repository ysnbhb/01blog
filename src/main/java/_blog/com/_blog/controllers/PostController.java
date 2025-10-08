package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.dto.PostConvert;
import _blog.com._blog.services.PostServ;
import _blog.com._blog.utils.PostReq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api")
public class PostController {
    PostServ postServ;

    public PostController(PostServ postServ) {
        this.postServ = postServ;
    }

    @PostMapping(path = "creat_post")
    public ResponseEntity<?> post(@Valid @ModelAttribute PostReq postReq, HttpServletRequest request) throws Exception {
        User user = (User) request.getAttribute("user");
        Post post = postServ.save(postReq, user);
        return ResponseEntity.ok(PostConvert.convertToPostReq(post));
    }

    @DeleteMapping(path = "delete_post")
    public ResponseEntity<?> delete(@RequestParam("id") String id, HttpServletRequest request) throws Exception {
        User user = (User) request.getAttribute("user");
        postServ.delete(Long.parseLong(id), user);
        return ResponseEntity.ok(id);
    }

    @GetMapping(path = "posts")
    public ResponseEntity<?> getPost(HttpServletRequest request,
            @RequestParam(defaultValue = "0", name = "offset") String offset)
            throws Exception {
        Long userid = (long) request.getAttribute("userId");
        int offsetint;
        try {
            offsetint = Integer.parseInt(offset);
        } catch (Exception e) {
            offsetint = 0;
        }
        return ResponseEntity.ok(PostConvert.convertToPostReq(postServ.getPost(userid, offsetint)));
    }

}
