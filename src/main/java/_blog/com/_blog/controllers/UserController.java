package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.services.UserServ;
import _blog.com._blog.utils.UserReq;
import jakarta.validation.Valid;

@RestController
public class UserController {
    private final UserServ userServ;

    public UserController(UserServ userServ) {
        this.userServ = userServ;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserReq userReq) throws UserExeption {
        userServ.save(userReq);
        return ResponseEntity.ok(userReq);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserReq user) {
        System.out.println(user.toString());
        return ResponseEntity.ok().body("");
    }

}
