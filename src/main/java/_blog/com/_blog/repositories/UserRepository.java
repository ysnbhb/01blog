package _blog.com._blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRoleNot(String role);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
