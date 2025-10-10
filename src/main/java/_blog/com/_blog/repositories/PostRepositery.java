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

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;

@Repository
public interface PostRepositery extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    void deleteAllByUser(User user);
    @Query(value = """
            SELECT
                p.id,
                p.content,
                p.url_photo AS urlPhoto,
                p.type_photo AS typePhoto,
                p.hide as Ishide,
                u.name AS name,
                u.last_name AS lastName,
                u.uuid AS uuid,
                u.url_photo AS userPhoto,
                u.username AS username,
                p.created_at AS createdAt,
                COUNT(DISTINCT cm.id) AS total_comments,
                COUNT(DISTINCT l.id) AS total_likes,
                EXISTS (
                    SELECT 1
                    FROM reactions l2
                    WHERE l2.post_id = p.id AND l2.user_id = :userId
                ) AS isLiked
            FROM posts p
            JOIN users u ON p.user_id = u.id
            LEFT JOIN comments cm ON cm.post_id = p.id
            LEFT JOIN reactions l ON l.post_id = p.id
            WHERE p.hide = :hide
            GROUP BY
                p.id, p.content, p.url_photo, p.type_photo,
                u.name, u.last_name, u.uuid, u.url_photo, u.username, p.created_at
            ORDER BY p.created_at DESC
            LIMIT 10 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> getPosts(@Param("userId") Long userid, @Param("offset") int offset, @Param("hide") boolean hide);

    @Modifying
    @Transactional
    @Query(value = "UPDATE posts SET hide = :hide WHERE id = :PostId", nativeQuery = true)
    int updateHideStatus(@Param("postId") long post_id, @Param("hide") boolean hide);
}
