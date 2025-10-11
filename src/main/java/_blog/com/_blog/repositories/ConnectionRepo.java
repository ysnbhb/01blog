package _blog.com._blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Connection;

@Repository
public interface ConnectionRepo extends JpaRepository<Connection, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM connection WHERE follower_id = :me AND following_id = :userId)", nativeQuery = true)
    boolean isfollowing(@Param("me") Long me, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM connection WHERE follower_id = :me AND following_id = :userId", nativeQuery = true)
    int deleteConnetion(@Param("me") Long me, @Param("userId") Long userId);

    @Query(value = "SELECT COUNT(*) FROM connectio WHERE  following_id = :userId", nativeQuery = true)
    int countOffolloewr(@Param("userId") Long userId);
}
