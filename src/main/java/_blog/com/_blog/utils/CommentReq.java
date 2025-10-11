package _blog.com._blog.utils;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentReq {
    private long id;
    @NotBlank(message = "content must be not null")
    @Size(min = 3, max = 255, message = "content must be between 3 and 255")
    private String content;
    private Long post_id;
    private UserReq user;
    private int numOflike;
    private LocalDateTime createdAt;
    private boolean isliked;
}
