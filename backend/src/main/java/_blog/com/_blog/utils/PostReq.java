package _blog.com._blog.utils;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostReq {
    private long id;
    private UserReq user;
    @NotBlank(message = "content must be not null")
    @Size(min = 3, message = "content must be more than 3 ")
    private String content;
    private String urlPhot;
    private String typePhoto;
    private int numOflike;
    private int numOfcomment;
    private boolean isliked;
    @JsonIgnore
    private MultipartFile photo;
    private LocalDateTime createdAt;
}
