package _blog.com._blog.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "connection")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id", nullable = false)
    private UserEntity follower;
    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "id", nullable = false)
    private UserEntity following;
}
