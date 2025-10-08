package _blog.com._blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoleNot(String role);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String username);

    @Query(value = "SELECT * FROM users ORDER BY created_at LIMIT 10 OFFSET :offset", nativeQuery = true)
    List<User> findAllWithOffset(@Param("offset") int offset);

}
