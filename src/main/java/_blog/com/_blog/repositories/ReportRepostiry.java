package _blog.com._blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import _blog.com._blog.Entity.Report;

public interface ReportRepostiry extends JpaRepository<Report, Long> {
    @Query(value = "SELECT * FROM report WHERE post_id = null ORDER BY created_at GROUP BY user_id LIMIT 20 OFFSET :offset")
    List<Report> findReportsUser(@Param("offset") int offset);

    @Query(value = "SELECT * FROM report WHERE to_userid = null ORDER BY created_at GROUP BY user_id LIMIT 20 OFFSET :offset")
    List<Report> findReportsPost(@Param("offset") int offset);
}
