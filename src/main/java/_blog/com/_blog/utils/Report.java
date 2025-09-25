package _blog.com._blog.utils;

import lombok.Data;

@Data
public class Report {
    private int id;
    private int userId;
    private String repored_userName;
    private String casReport;
    private String text;
}
