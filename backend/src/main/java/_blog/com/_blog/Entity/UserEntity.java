package _blog.com._blog.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reportsSent = new ArrayList<>();
    @OneToMany(mappedBy = "reportedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reportsReceived = new ArrayList<>();
    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notifacation> formuser = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notifacation> to_user = new ArrayList<>();
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Connection> followings = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Connection> followers = new ArrayList<>();

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
