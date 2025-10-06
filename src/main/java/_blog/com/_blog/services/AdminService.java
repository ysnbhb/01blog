package _blog.com._blog.services;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final PostRepositery postRepository;

    public AdminService(UserRepository userRepository, PostRepositery postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void delet(String uuid) throws UserExeption {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserExeption(400, "User not found"));

        // Delete all posts first
        postRepository.deleteAllByUser(user);

        // Now delete the user
        userRepository.delete(user);
    }
}
