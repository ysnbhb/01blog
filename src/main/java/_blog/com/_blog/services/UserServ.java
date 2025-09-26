package _blog.com._blog.services;

import org.springframework.stereotype.Service;
import _blog.com._blog.utils.UserReq;
import _blog.com._blog.Entity.User;
import _blog.com._blog.repositories.UserRepository;

@Service
public class UserServ {
    private final UserRepository userRepository;

    public UserServ(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserReq userReq) {
        if (!userReq.isValid()) {
            throw new IllegalArgumentException("Invalid user data");
        }

        User user = new User();
        user.setEmail(userReq.getEmail());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setDateOfBirth(userReq.getDateOfBirth());
        user.setName(userReq.getName());
        user.setLastName(userReq.getLastName());
        user.setUrlPhoto(userReq.getUrlPhoto());
        return userRepository.save(user);
    }
}
