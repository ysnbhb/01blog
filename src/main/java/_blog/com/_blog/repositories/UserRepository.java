package _blog.com._blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import _blog.com._blog.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
