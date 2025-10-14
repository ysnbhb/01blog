package _blog.com._blog.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoleNot(String role);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String username);

    @Query(value = "SELECT * FROM users ORDER BY created_at LIMIT 10 OFFSET :offset", nativeQuery = true)
    List<User> findAllWithOffset(@Param("offset") int offset);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET status = :status WHERE uuid = :uuid", nativeQuery = true)
    int updateUserStatus(@Param("uuid") String uuid, @Param("status") String status);

    @Query(value = """
            SELECT name
                lastName ,
                username ,
                dateOfBirth ,
                urlPhoto ,
                role ,
                id
                (SELECT COUNT(*) FROM connection WHERE follower_id = :uuid ) AS following,
                (SELECT COUNT(*) FROM connection WHERE following_id = :uuid ) AS follower,
                WHERE uuid = :uuid
            """ , nativeQuery = true)
    Map<String, Object> findUser(@Param("uuid") String uuid);

    @Query("""
                SELECT u
                FROM User u
                WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(u.name) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
            """)
    List<User> searchUsers(@Param("query") String query);

}
