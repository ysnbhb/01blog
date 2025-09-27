package _blog.com._blog.utils;

import lombok.Data;

@Data
public class Report {
    private int id;
    private int userId;
    private String post_id;
    private String repored_userName;
    private String casReport;
    private String text;
}
