package _blog.com._blog.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;
    @Column(unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @JsonIgnore
    private String password;
    @Column(name = "dateOfBirth", nullable = false)
    private String dateOfBirth;
    private String name;
    private String lastName;
    private String urlPhoto;
    private String role;
    private String status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (uuid == null || uuid.isEmpty()) {
            uuid = UUID.randomUUID().toString();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
