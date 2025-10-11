package _blog.com._blog.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.services.ConnectionSrv;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConnectionControl {
    private final ConnectionSrv connectionSrv;

    @PostMapping("follow")
    public Map<String, Object> follow(@RequestAttribute("user") User user, @RequestParam("uuid") String uuid)
            throws ProgramExeption {
        return connectionSrv.follow(user, uuid);
    }
}
