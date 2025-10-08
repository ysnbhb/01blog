package _blog.com._blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import _blog.com._blog.Entity.Reaction;

public interface ReactionRepo extends JpaRepository<Reaction, Long> {

}
