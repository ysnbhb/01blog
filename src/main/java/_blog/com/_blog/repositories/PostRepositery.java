package _blog.com._blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.Post;

@Repository
public interface PostRepositery extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    
}
