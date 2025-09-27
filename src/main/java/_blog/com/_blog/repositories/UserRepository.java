package _blog.com._blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByRoleNot(String role);
}
