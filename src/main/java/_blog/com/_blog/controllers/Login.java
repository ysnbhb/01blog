package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.model.User;

@RestController
public class Login {
    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@ModelAttribute User user) {
        System.out.println(user.toString());
        return ResponseEntity.ok().body("");
        // return "";
    }

}
