package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;
import _blog.com._blog.services.UserServ;
import _blog.com._blog.utils.UserReq;

@RestController
public class UserController {
    private final UserServ userServ;

    public UserController(UserServ userServ) {
        this.userServ = userServ;
    }

    @PostMapping(path = "/register")
    public User registerUser(@ModelAttribute UserReq user) {
        return userServ.save(user);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserReq user) {
        System.out.println(user.toString());
        return ResponseEntity.ok().body("");
        // return "";
    }

}
