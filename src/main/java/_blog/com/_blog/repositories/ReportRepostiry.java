package _blog.com._blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import _blog.com._blog.Entity.Report;

public interface ReportRepostiry extends JpaRepository<Report, Long> {

}
