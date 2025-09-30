package _blog.com._blog.controllers;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute UserReq userReq) {
        try {
            System.out.println(userReq.toString());
            userServ.save(userReq);
            return ResponseEntity.ok(userReq);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Email or username already exists"));
        } catch (UserExeption e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserReq user) {
        System.out.println(user.toString());
        return ResponseEntity.ok().body("");
    }

}
