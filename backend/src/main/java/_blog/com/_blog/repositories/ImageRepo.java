package _blog.com._blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import _blog.com._blog.Entity.Image;

public interface ImageRepo extends JpaRepository<Image, Long> {

    List<Image> findImgesByPostId(Long postId);

    void deleteByUrl(String url);
    void deleteImgesByPostId(Long post_id);
}
