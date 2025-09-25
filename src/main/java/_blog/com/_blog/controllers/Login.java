package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.utils.UserReq;

@RestController
public class Login {
    @PostMapping(path = "/register")
    public UserReq registerUser(@ModelAttribute UserReq user) {
        System.out.println(user.toString());
        return user;
        // return "";
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserReq user) {
        System.out.println(user.toString());
        return ResponseEntity.ok().body("");
        // return "";
    }

}
