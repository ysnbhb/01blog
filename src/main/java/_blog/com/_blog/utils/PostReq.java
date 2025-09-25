package _blog.com._blog.utils;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostReq {
    private int userid;
    private String text;
    private MultipartFile photo;
    private String urlPhot;
}


