package _blog.com._blog.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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
    public ResponseEntity<?> delete(@RequestParam(defaultValue = "0", name = "postId") Long id,
            HttpServletRequest request) throws Exception {
        System.out.println(id);
        User user = (User) request.getAttribute("user");
        postServ.delete(id, user);
        return ResponseEntity.ok(id);
    }

    @GetMapping(path = "posts")
    public ResponseEntity<?> getPosts(HttpServletRequest request,
            @RequestParam(defaultValue = "0", name = "offset") Long offset)
            throws Exception {
        Long userid = (long) request.getAttribute("userId");
        return ResponseEntity
                .ok(postServ.getPosts(userid, offset.intValue()).stream().map(PostConvert::convertToPostReq));
    }

    @GetMapping(path = "users_post")
    public ResponseEntity<?> getUserPost(@RequestAttribute("userId") Long userid,
            @RequestParam(defaultValue = "0", name = "offset") Long offset,
            @RequestParam(defaultValue = "0", name = "uuid") String uuid)
            throws Exception {
        return ResponseEntity
                .ok(postServ.getUserPosts(userid, offset.intValue(), uuid).stream().map(PostConvert::convertToPostReq));
    }

    @GetMapping(path = "post")
    public ResponseEntity<?> getPost(@RequestAttribute("userId") Long userid,
            @RequestParam(defaultValue = "0", name = "postId") Long postid)
            throws Exception {
        Map<String, Object> post = postServ.getPost(userid, postid);
        return post != null && post.size() > 0
                ? ResponseEntity.ok(PostConvert.convertToPostReq(postServ.getPost(userid, postid)))
                : ResponseEntity.status(404).body("Post not found");
    }

}
