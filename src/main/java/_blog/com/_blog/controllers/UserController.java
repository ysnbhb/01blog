package _blog.com._blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.services.JwtService;
import _blog.com._blog.services.UserServ;
import _blog.com._blog.utils.UserReq;
import jakarta.validation.Valid;

@RestController
public class UserController {
    private final UserServ userServ;
    private final JwtService jwtService;

    public UserController(UserServ userServ, JwtService jwtService) {
        this.userServ = userServ;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserReq userReq) throws UserExeption {
        User user = userServ.save(userReq);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserReq userReq) throws UserExeption {

        User user = userServ.login(userReq);
        System.out.println(user.toString());
        System.out.println(userReq.toString());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(token);
    }

}
