package _blog.com._blog.utils;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserReq {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank(message = "name must be not null")
    @Email(message = "Email should be valid")
    private String email;
    private String uuid;
    @UserName
    @Size(max = 10, min = 3, message = "invalid username")
    private String username;
    @ValidDateOfBirth
    private String dateOfBirth;
    @NotBlank(message = "name must be not null")
    @Size(max = 10, min = 3, message = "Name should be min 3 and max 6")
    @NameValid
    private String name;
    @Size(max = 10, min = 3, message = "Last Name should be  min 3 and max 10")
    @NotBlank(message = "last name must be not null")
    @NameValid
    private String lastName;
    @JsonIgnore
    private MultipartFile photo;
    private String urlPhoto;
    private int followers;
    private int following;
    private boolean mayAcount;
    private boolean hasCon;
    private String role;
    private String stutes;

}
