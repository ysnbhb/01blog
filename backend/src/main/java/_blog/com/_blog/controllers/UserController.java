package _blog.com._blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.UserEntity;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.UserConvert;
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
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserReq userReq) throws ProgramExeption {
        UserEntity user = userServ.save(userReq);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserReq userReq) throws ProgramExeption {
        UserEntity user = userServ.login(userReq);
        String token = jwtService.generateToken(user);
        if (user.getStatus().equals("BANNED")) {
            throw new ProgramExeption(400, "You are Banned From Same reason");
        }
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("api/me")
    public UserReq me(@RequestAttribute("user") UserEntity user) {
        return UserConvert.convertToUserReq(user);
    }

    @GetMapping("api/profile/{uuid}")
    public UserReq profile(@RequestAttribute("user") UserEntity user, @PathVariable("uuid") String uuid) throws Exception {
        return userServ.profile(uuid, user);
    }

    @GetMapping("/search")
    public List<UserReq> searchUsers(@RequestParam("query") String query) {
        return userServ.searchUsers(query);
    }
}
