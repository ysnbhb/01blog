package _blog.com._blog.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                cm.created_at AS createdAt,
                COUNT(DISTINCT l.id) AS total_likes,
                EXISTS (
                    SELECT 1
                    FROM reactions l2
                    WHERE l2.comment_id = cm.id AND l2.user_id = :userId
                ) AS isLiked
            FROM comments cm
            JOIN users u ON cm.user_id = u.id
            JOIN posts p ON cm.post_id = p.id
            LEFT JOIN reactions l ON l.comment_id = cm.id
            WHERE p.hide = :hide AND p.id = :postId
            ORDER BY cm.created_at DESC
            LIMIT 10 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> getComments(
            @Param("userId") Long userId,
            @Param("offset") int offset,
            @Param("hide") boolean hide,
            @Param("postId") Long postId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comments WHERE post_id=:post_id ", nativeQuery = true)
    void deleteByPostid(@Param("post_id") Long post_id);
}
