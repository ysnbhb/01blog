package _blog.com._blog.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Report;

@Repository
public interface ReportRepostiry extends JpaRepository<Report, Long> {
    @Query(value = """
            SELECT
                u2.username AS reported_username,
                u2.uuid AS reported_uuid,
                u2.name AS reported_name,
                u2.last_name AS reported_last_name,
                u2.url_photo AS reported_url_photo,
                COUNT(r.id) AS report_count
            FROM report r
            JOIN users AS u2 ON u2.id = r.to_userid
            WHERE r.post_id IS NULL
            GROUP BY
                u2.username, u2.uuid, u2.name, u2.last_name, u2.url_photo
            ORDER BY MAX(r.created_at) DESC
            LIMIT 20 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> findReportsUser(@Param("offset") int offset);

    @Query(value = """
            SELECT
                u2.username AS username,
                u2.uuid AS uuid,
                u2.name AS name,
                u2.last_name AS last_name,
                u2.url_photo AS url_photo,
                p.content AS post_content,
                p.type_photo AS type_photo,
                p.url_photo AS post_url_photo,
                COUNT(r.id) AS report_count

            FROM report r
            JOIN posts AS p ON p.id = r.post_id
            JOIN users AS u2 ON u2.id = p.user_id
            WHERE r.to_userid IS NULL

            GROUP BY
                r.reason,
                u2.username, u2.uuid, u2.name, u2.last_name, u2.url_photo,
                p.content, p.type_photo, p.url_photo

            ORDER BY MAX(r.created_at) DESC
            LIMIT 20 OFFSET :offset
            """, nativeQuery = true)
    List<Map<String, Object>> findReportsPost(@Param("offset") int offset);

    @Modifying
    @Transactional
    @Query(value = "DELETE  FROM report WHERE post_id = :postId", nativeQuery = true)
    void deleteByPost(@Param("postId") long post_id);
}
