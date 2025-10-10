package _blog.com._blog.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class ReportReq {
    private Long id;
    private String uuid;
    private Long postId;
    private PostReq post;
    @Null
    private UserReq user;
    @Null
    private UserReq userReported;
    @NotBlank
    private String reason;
    @Null
    private String text;
}
