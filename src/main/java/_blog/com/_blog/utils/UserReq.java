package _blog.com._blog.utils;

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
    private String urlPhoto;

    public boolean isValid() {
        if (password == null || password.length() < 8) {
            return false;
        }
        if (name == null || name.length() < 3) {
            return false;
        }
        if (lastName == null || lastName.length() < 3) {
            return false;
        }
        if (email == null || email.length() < 3) {
            return false;
        }
        return true;
    }
}
