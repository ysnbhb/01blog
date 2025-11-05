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

@Repository
public interface PostRepositery extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    @Query(value = """
            SELECT
                p.id,
                p.title,
                CAST(p.content AS TEXT) AS content,
                p.hide as Ishide,
                u.name AS name,
                u.last_name AS lastName,
                u.uuid AS uuid,
                u.url_photo AS userPhoto,
                u.username AS username,
                u.role,
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
                p.id, p.content, p.title,
                u.name, u.last_name, u.uuid, u.url_photo, u.username, p.created_at, u.role
            ORDER BY p.created_at DESC
            LIMIT 10 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> getPosts(@Param("userId") Long userid, @Param("offset") int offset,
            @Param("hide") boolean hide);

    @Query(value = """
            SELECT
                p.id,
                p.title,
                CAST(p.content AS TEXT) AS content,
                p.hide as Ishide,
                u.name AS name,
                u.last_name AS lastName,
                u.uuid AS uuid,
                u.url_photo AS userPhoto,
                u.username AS username,
                u.role,
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
            WHERE p.hide = :hide AND (EXISTS (SELECT 1 FROM connection WHERE follower_id = :userId AND following_id = u.id) OR u.id = :userId)
            GROUP BY
                p.id, p.content, p.title,
                u.name, u.last_name, u.uuid, u.url_photo, u.username, p.created_at, u.role
            ORDER BY p.created_at DESC
            LIMIT 10 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> getsubPosts(@Param("userId") Long userid, @Param("offset") int offset,
            @Param("hide") boolean hide);

    @Modifying
    @Transactional
    @Query(value = "UPDATE posts SET hide = :hide WHERE id = :postId", nativeQuery = true)
    int updateHideStatus(@Param("postId") long post_id, @Param("hide") boolean hide);

    @Query(value = """
            SELECT
                p.id,
                CAST(p.content AS TEXT) AS content,
                p.hide as Ishide,
                u.name AS name,
                u.last_name AS lastName,
                u.uuid AS uuid,
                u.role,
                p.title,
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
            WHERE p.hide = :hide AND u.uuid = :uuid
            GROUP BY
                p.id, p.content, p.title,
                u.name, u.last_name, u.uuid, u.url_photo, u.username, p.created_at , u.role
            ORDER BY p.created_at DESC
            LIMIT 10 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> getUserPosts(@Param("userId") Long userid, @Param("offset") int offset,
            @Param("hide") boolean hide, @Param("uuid") String uuid);

    @Query(value = """
            SELECT
                p.id,
                CAST(p.content AS TEXT) AS content,
                p.hide as Ishide,
                u.name AS name,
                p.title,
                u.last_name AS lastName,
                u.uuid AS uuid,
                u.url_photo AS userPhoto,
                u.username AS username,
                u.role,
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
            WHERE p.hide = :hide AND p.id = :post_id
            GROUP BY
                p.id, p.content, p.title,
                u.name, u.last_name, u.uuid, u.url_photo, u.username, p.created_at , u.role
            ORDER BY p.created_at DESC
            """, nativeQuery = true)
    Map<String, Object> getPost(@Param("userId") Long userid, @Param("post_id") Long offset,
            @Param("hide") boolean hide);
}
