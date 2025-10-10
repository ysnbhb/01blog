package _blog.com._blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.PostConvert;
import _blog.com._blog.services.AdminService;
import _blog.com._blog.utils.UserReq;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin")
public class AdminControl {
    private final AdminService adminServ;

    public AdminControl(AdminService adminServ) {
        this.adminServ = adminServ;
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<?> deleteUser(@RequestParam("uuid") String uuid) throws ProgramExeption {
        adminServ.delete(uuid);
        return ResponseEntity.ok(uuid);
    }

    @GetMapping("/users")
    public List<UserReq> getUsers(@RequestParam(defaultValue = "0", name = "offset") int offset) {
        System.out.println(offset);
        return adminServ.getUsers(offset);
    }

    @PutMapping("/hide_post")
    public boolean hidePost(@RequestParam(defaultValue = "0", name = "offset") Long post_id) throws ProgramExeption {
        return adminServ.hidePost(post_id);
    }

    @GetMapping(path = "hide_posts")
    public ResponseEntity<?> getHidePost(HttpServletRequest request,
            @RequestParam(defaultValue = "0", name = "offset") Long offset)
            throws Exception {
        Long userid = (long) request.getAttribute("userId");
        return ResponseEntity.ok(PostConvert.convertToPostReq(adminServ.getHidePost(userid, offset.intValue())));
    }
}
