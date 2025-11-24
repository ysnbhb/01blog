package _blog.com._blog.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.Report;

@Repository
public interface ReportRepostiry extends JpaRepository<Report, Long> {
    @Query(value = """
            SELECT
                r.to_userid AS toUserId,
                u2.username AS username,
                u2.uuid AS uuid,
                u2.name AS name,
                u2.last_name AS lastName,
                u2.url_photo AS userPhoto,
                u2.status ,
                u2.role ,
                COUNT(r.id) AS reportCount,
                MAX(r.created_at) AS lastReportAt
            FROM report r
            JOIN users u2 ON u2.id = r.to_userid
            WHERE r.post_id IS NULL
            GROUP BY
                r.to_userid,
                u2.username, u2.uuid, u2.name, u2.last_name, u2.url_photo , u2.status , u2.role
            ORDER BY lastReportAt DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findReportsUser();

    @Query(value = """
            SELECT
               COUNT(*)
            FROM report r
            WHERE r.post_id IS NULL
            """, nativeQuery = true)
    int CountReportsUser();

    @Query(value = """
            SELECT
               COUNT(*)
            FROM report r
            WHERE r.to_userid IS NULL
            """, nativeQuery = true)
    int CountReportsPost();

    @Query(value = """
            SELECT
                r.post_id AS postId,
                u2.username AS username,
                u2.uuid AS uuid,
                u2.name AS name,
                u2.last_name AS lastName,
                u2.url_photo AS userPhoto,
                SUBSTRING(p.content,1,100) AS content,
                SUBSTRING(p.title,1,20) AS title,
                p.hide AS hide,
                COUNT(r.id) AS reportCount,
                MAX(r.created_at) AS lastReportAt
            FROM report r
            JOIN posts p ON p.id = r.post_id
            JOIN users u2 ON u2.id = p.user_id
            WHERE r.to_userid IS NULL
            GROUP BY
                r.post_id,
                u2.username, u2.uuid, u2.name, u2.last_name, u2.url_photo,
                p.content, p.title  , p.hide
            ORDER BY lastReportAt DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findReportsPost();

    @Query(value = """
            SELECT
            r.id AS id,
               r.reason,
               u1.username,
               u1.url_photo,
               r.created_at AS createdAt
            FROM report r
            JOIN users u2 ON u2.id = r.to_userid
            JOIN users u1 ON u1.id = r.user_id
            WHERE u2.uuid = :uuid
            GROUP BY
                r.reason ,u1.username ,u1.url_photo , r.created_at , r.id
            ORDER BY MAX(r.created_at) DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findReasonUser(@Param("uuid") String uuid);

    @Query(value = """
            SELECT
                r.id AS id,
               r.reason,
               u1.username,
               u1.url_photo,
               r.created_at AS createdAt
            FROM report r
            JOIN users u1 ON u1.id = r.user_id
            WHERE r.post_id = :postId
            GROUP BY
                r.reason ,u1.username ,u1.url_photo , r.created_at , r.id
            ORDER BY MAX(r.created_at) DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findReasonPost(@Param("postId") Long postid);
}
