package _blog.com._blog.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import _blog.com._blog.utils.Upload;
import _blog.com._blog.utils.UserReq;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.dto.UserConvert;
import _blog.com._blog.repositories.UserRepository;

@Service
public class UserServ {
    private final UserRepository userRepository;

    public UserServ(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserReq userReq) throws UserExeption {
        if (userRepository.existsByEmail(userReq.getEmail())) {
            throw new UserExeption(400, "email already exists try other one");
        }
        String userName = userReq.getUsername();
        if (userName != null && userRepository.existsByUsername(userName)) {
            throw new UserExeption(400, "username already exists try other one");

        }
        if (userName == null) {
            do {
                userName = generateUsername(userReq.getName(), userReq.getLastName());
            } while (userRepository.existsByUsername(userName));
        }
        userReq.setUsername(userName);
        var photo = userReq.getPhoto();
        if (photo != null) {
            userReq.setUrlPhoto((Upload.saveImage(photo)));
        } else {
            userReq.setUrlPhoto("default-avatar.jpg");
        }

        return userRepository.save(UserConvert.convertToUser(userReq));
    }

    public static String generateUsername(String name, String lastName) {
        Random rand = new Random(System.currentTimeMillis());

        List<String> formats = Arrays.asList(
                name + lastName,
                name + "." + lastName,
                name + "_" + lastName,
                name.substring(0, 3) + "@" + lastName.substring(0, 3),
                name + (100 + rand.nextInt(900)),
                lastName + (100 + rand.nextInt(900)));

        String username = formats.get(rand.nextInt(formats.size()));
        return username.toLowerCase();
    }
}
