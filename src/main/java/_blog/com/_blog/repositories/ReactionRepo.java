package _blog.com._blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Reaction;

public interface ReactionRepo extends JpaRepository<Reaction, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM reactions WHERE post_id = :postId AND user_id = :userId)", nativeQuery = true)
    boolean isLiked(@Param("postId") long post_id, @Param("userId") long user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE  FROM reactions WHERE post_id = :postId AND user_id = :userId", nativeQuery = true)
    int deleteByPostIdAndUserId(@Param("postId") long post_id, @Param("userId") long user_id);

    @Query(value = "SELECT COUNT(*) FROM reactions WHERE post_id = :postId ", nativeQuery = true)
    int countOfLikedPost(@Param("postId") long post_id);
}
