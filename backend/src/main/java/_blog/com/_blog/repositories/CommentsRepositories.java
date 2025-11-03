package _blog.com._blog.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.Comment;

@Repository
public interface CommentsRepositories extends JpaRepository<Comment, Long> {
    @Query(value = """
            SELECT
                cm.id,
                cm.content,
                u.name AS name,
                u.last_name AS lastName,
                u.uuid AS uuid,
                u.url_photo AS userPhoto,
                u.username AS username,
                u.role,
                cm.created_at AS createdAt
            FROM comments cm
            JOIN users u ON cm.user_id = u.id
            JOIN posts p ON cm.post_id = p.id
            WHERE p.hide = :hide AND p.id = :postId
            GROUP BY
                cm.id, cm.content,
                u.name, u.last_name, u.uuid, u.url_photo, u.username, cm.created_at, u.role
            ORDER BY cm.created_at DESC
            LIMIT 10 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> getComments(
            @Param("userId") Long userId,
            @Param("offset") int offset,
            @Param("hide") boolean hide,
            @Param("postId") Long postId);

}
