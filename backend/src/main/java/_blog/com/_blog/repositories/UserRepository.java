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

import _blog.com._blog.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByRoleNot(String role);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUuid(String uuid);

    Optional<UserEntity> findByEmail(String username);

    @Query(value = "SELECT * FROM users ORDER BY created_at LIMIT 10 OFFSET :offset", nativeQuery = true)
    List<UserEntity> findAllWithOffset(@Param("offset") int offset);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET status = :status WHERE uuid = :uuid", nativeQuery = true)
    int updateUserStatus(@Param("uuid") String uuid, @Param("status") String status);

    @Query(value = """
            SELECT
                u.id,
                u.name,
                u.last_name,
                u.username,
                u.url_photo,
                u.role,
                u.uuid,
                COALESCE(fc.following_count, 0) AS following,
                COALESCE(fl.follower_count, 0) AS follower
            FROM users u
            LEFT JOIN (
                SELECT follower_id, COUNT(*) AS following_count
                FROM connection
                GROUP BY follower_id
            ) fc ON fc.follower_id = u.id
            LEFT JOIN (
                SELECT following_id, COUNT(*) AS follower_count
                FROM connection
                GROUP BY following_id
            ) fl ON fl.following_id = u.id
            WHERE u.uuid = :uuid
            """, nativeQuery = true)
    Map<String, Object> findUser(@Param("uuid") String uuid);

    @Query("""
                SELECT u
                FROM UserEntity AS u
                WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(u.name) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER (CONCAT(u.lastName , " " ,u.name )) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER (CONCAT(u.name , " " ,u.lastName )) LIKE LOWER(CONCAT('%', :query, '%'))
            """)
    List<UserEntity> searchUsers(@Param("query") String query);

}
