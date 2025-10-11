package _blog.com._blog.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;

@RestController
public class ConnectionControl {
    public ConnectionControl() {
        
    }
    @PostMapping("follow")
    public void follow(@RequestAttribute("user") User user, @RequestParam("uuid") String uuid) {

    }
}
