package _blog.com._blog.utils;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportReq {
    private Long id;
    private String uuid;
    private Long postId;
    @Null(message = "must be null")
    private PostReq post;
    @Null(message = "must be null")
    private UserReq user;
    @Null(message = "must be null")
    private UserReq userReported;
    @NotBlank(message = "must be correct")
    @Size(min = 3 , message =  "must be more than 3")
    private String reason;
    @Null(message = "must be null")
    private String text;
}
