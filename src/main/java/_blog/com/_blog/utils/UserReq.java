package _blog.com._blog.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import lombok.Data;

@Data
public class UserReq {
    @JsonIgnore
    private String password;
    private String email;
    private String username;
    private String dateOfBirth;
    private String name;
    private String lastName;
    @JsonIgnore
    private MultipartFile photo;
    private String urlPhoto;
    private int followers;
    private int following;
    private boolean mayAcount;

    @JsonIgnore
    public String isValid() {
        if (password == null || password.length() < 8) {
            return "password must be at less 8 charat";
        }
        if (name == null || name.length() < 3) {
            return "name is too short";
        }
        if (lastName == null || lastName.length() < 3) {
            return "name is too short";
        }
        if (!isValidEmailAddress(email)) {
            return "unvalid emainl";
        }
        if (!isValidDate())
            return "unvalid date of Birth";
        if (username != null && username.length() < 3)
            return "username is too short";
        try {
            if (photo != null && !isRealPhoto(photo)) {
                return "photo is unvalid";
            }
        } catch (Exception e) {
            return "photo is unvalid";
        }

        return null;
    }

    @JsonIgnore
    public boolean isValidDate() {
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth); // e.g. "2005-09-27"
            LocalDate today = LocalDate.now();

            // Check if birthDate is in the future (invalid)
            if (birthDate.isAfter(today)) {
                return false;
            }

            // Calculate age
            int age = Period.between(birthDate, today).getYears();

            return age >= 16;
        } catch (Exception e) {
            return false;
        }
    }

    @JsonIgnore
    public static boolean isRealPhoto(MultipartFile file) throws IOException {
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
    public static boolean isValidImage(byte[] data) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
            return img != null;
        } catch (IOException e) {
            return false;
        }
    }

    @JsonIgnore
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}
