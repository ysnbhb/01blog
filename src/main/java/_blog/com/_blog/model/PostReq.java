package _blog.com._blog.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostReq {
    private int userid;
    private String text;
    private MultipartFile photo;
    private String urlPhot;
}


