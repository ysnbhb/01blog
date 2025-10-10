package _blog.com._blog.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.Report;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.ReportConvet;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.ReportRepostiry;
import _blog.com._blog.repositories.UserRepository;
import _blog.com._blog.utils.ReportReq;

@Service
public class ReportSer {
    private final UserRepository userRepository;
    private final PostRepositery postRepositery;
    private final ReportRepostiry reportRepostiry;

    public ReportSer(UserRepository userRepository, PostRepositery postRepositery, ReportRepostiry reportRepostiry) {
        this.postRepositery = postRepositery;
        this.userRepository = userRepository;
        this.reportRepostiry = reportRepostiry;
    }

    public void reportPost(ReportReq report, User user) throws ProgramExeption {
        if (report.getPostId() == null)
            return;
        Post post = postRepositery.findById(report.getPostId())
                .orElseThrow(() -> new ProgramExeption(400, "Post not found"));
        Report report2 = new Report();
        report2.setPost(post);
        report2.setReason(report.getReason());
        report2.setReporter(user);
        reportRepostiry.save(report2);
    }

    public void reportUser(ReportReq report, User user) throws ProgramExeption {
        User user2 = userRepository.findByUuid(report.getUuid())
                .orElseThrow(() -> new ProgramExeption(400, "Post not found"));
        Report report2 = new Report();
        report2.setReportedUser(user2);
        report2.setReason(report.getReason());
        report2.setReporter(user);
        reportRepostiry.save(report2);
    }

    public List<Map<String, Object>> findReportPost(int offset) {
        return reportRepostiry.findReportsPost(offset);

    }

    public List<Map<String, Object>> findReportUser(int offset) {
        return reportRepostiry.findReportsUser(offset);
    }
}
