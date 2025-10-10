package _blog.com._blog.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class ReportReq {
    private int id;
    private String uuid;
    private Long postId;
    @Null
    private UserReq user;
    @NotBlank
    private String reason;
    @Null
    private String text;
}
