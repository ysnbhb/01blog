package _blog.com._blog.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserReq {
    private String password;
    private String email;
    private String username;
    private String dateOfBirth;
    private String name;
    private String lastName;
    private MultipartFile photo;
    private String urlPhot ;
}
