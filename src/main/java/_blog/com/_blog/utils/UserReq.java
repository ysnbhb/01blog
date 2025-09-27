package _blog.com._blog.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserReq {
    @JsonIgnore
    private String password;
    private String email;
    private String username = "dfgfdgfdh";
    private String dateOfBirth = "jdsjhdsjh";
    private String name;
    private String lastName;
    @JsonIgnore
    private MultipartFile photo;
    private String urlPhoto = "dsjjhjksdh";
    private int followers;
    private int following;
    private boolean mayAcount;
    @JsonIgnore
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

    @JsonIgnore
    public boolean isRealPhoto(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty())
            return false;

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return false;
        }
        byte[] bytes = file.getBytes();
        return isValidImage(bytes);
    }

    @JsonIgnore
    public boolean isValidImage(byte[] data) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
            return img != null;
        } catch (IOException e) {
            return false;
        }
    }

}
