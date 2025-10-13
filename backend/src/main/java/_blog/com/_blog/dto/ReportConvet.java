package _blog.com._blog.dto;

import _blog.com._blog.Entity.Report;
import _blog.com._blog.utils.ReportReq;

public class ReportConvet {
    public static ReportReq convertToReportReq(Report report) {
        if (report == null)
            return null;
        ReportReq reportReq = new ReportReq();
        reportReq.setId(report.getId());
        reportReq.setPost(PostConvert.convertToPostReq(report.getPost()));
        reportReq.setUser(UserConvert.convertToUserReq(report.getReporter()));
        reportReq.setUserReported(UserConvert.convertToUserReq(report.getReportedUser()));
        reportReq.setReason(report.getReason());
        return reportReq;
    }
}
