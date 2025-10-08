package _blog.com._blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.services.AdminService;
import _blog.com._blog.utils.UserReq;

@RestController
@RequestMapping("admin")
public class AdminControl {
    private final AdminService userServ;

    public AdminControl(AdminService userServ) {
        this.userServ = userServ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("uuid") String uuid) throws UserExeption {
        userServ.delete(uuid);
        return ResponseEntity.ok(uuid);
    }

    @GetMapping("/users")
    public List<UserReq> getUsers(@RequestParam(defaultValue = "0", name = "offset") int offset) {
        System.out.println(offset);
        return userServ.getUsers(offset);
    }
}
