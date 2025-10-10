package _blog.com._blog.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Entity.User;
import _blog.com._blog.services.ReportSer;
import _blog.com._blog.utils.ReportReq;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class ReportControl {
    private final ReportSer reportSer;

    public ReportControl(ReportSer reportSer) {
        this.reportSer = reportSer;
    }

    @PostMapping("reportPost")
    public void reportPost(@Valid @RequestBody ReportReq report, @RequestAttribute("user") User user) throws Exception {

        reportSer.reportPost(report, user);
    }

    @PostMapping("reportUser")
    public void reportUser(@Valid @RequestBody ReportReq report, @RequestAttribute("user") User user) throws Exception {
        reportSer.reportUser(report, user);
    }

}
