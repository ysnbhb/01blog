package _blog.com._blog.utils;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserReq {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank(message = "name must be not null")
    private String email;
    private String username;
    private String dateOfBirth;
    @NotBlank(message = "name must be not null")
    @Size(min = 3, message = "hsdfhgjhdsf jhgsdjhgjdsf")
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

        return null;
    }

    @JsonIgnore
    public boolean isValidDate() {
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);
            LocalDate today = LocalDate.now();
            if (birthDate.isAfter(today)) {
                return false;
            }
            int age = Period.between(birthDate, today).getYears();

            return age >= 16;
        } catch (Exception e) {
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
