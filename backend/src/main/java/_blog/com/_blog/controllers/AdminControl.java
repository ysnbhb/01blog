package _blog.com._blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.services.AdminService;
import _blog.com._blog.services.ReportSer;
import _blog.com._blog.utils.UserReq;

@RestController
@RequestMapping("admin")
public class AdminControl {
    private final AdminService adminServ;
    private final ReportSer reportSer;

    public AdminControl(AdminService adminServ, ReportSer reportSer) {
        this.adminServ = adminServ;
        this.reportSer = reportSer;
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<?> deleteUser(@RequestParam("uuid") String uuid) throws ProgramExeption {
        adminServ.delete(uuid);
        return ResponseEntity.ok(Map.of("uuid", uuid));
    }

    @GetMapping("/users")
    public List<UserReq> getUsers(@RequestParam(defaultValue = "0", name = "offset") int offset,
            @RequestParam(defaultValue = "0", name = "limit") int limit) {
        offset = Math.max(0, offset) * Math.max(1, limit);
        return adminServ.getUsers(offset, limit);
    }

    @PutMapping("/hide_post")
    public boolean hidePost(@RequestParam(defaultValue = "0", name = "postId") Long post_id) throws ProgramExeption {
        return adminServ.hidePost(post_id);
    }

    @PostMapping(path = "banne_user")
    public boolean banneUser(@RequestParam("uuid") String uuid) throws Exception {
        return adminServ.banneUser(uuid);
    }

    @GetMapping(path = "reported")
    public List<Map<String, Object>> getReported(
            @RequestParam(name = "type") String type) throws Exception {
        if (type.equals("user")) {
            return reportSer.findReportUser();
        } else if (type.equals("post")) {
            return reportSer.findReportPost();
        }
        return null;
    }

    @GetMapping(path = "reasone/user")
    public List<Map<String, Object>> getReasoneReported(
            @RequestParam(name = "uuid") String uuid) throws Exception {

        return reportSer.findReasonUser(uuid);
    }

    @GetMapping(path = "reasone/post")
    public List<Map<String, Object>> getReasonePost(
            @RequestParam(name = "postid") Long postid) throws Exception {
        return reportSer.findReasonPost(postid);
    }

    @GetMapping(path = "dashboardStatus")
    public Map<String, Object> DashboardStats() {
        return adminServ.DashboardStats();
    }
}
