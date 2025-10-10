package _blog.com._blog.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.utils.ReportReq;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class ReportControl {
    @PostMapping("reportPost")
    public void reportPost(@Valid @RequestBody ReportReq report) {

    }

}
