package _blog.com._blog.services;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.Report;
// import _blog.com._blog.Entity.Report;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.ReportRepostiry;
import _blog.com._blog.repositories.UserRepository;
import _blog.com._blog.utils.ReportReq;

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
}
