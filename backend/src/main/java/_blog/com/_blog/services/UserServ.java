package _blog.com._blog.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.utils.Upload;
import _blog.com._blog.utils.UserReq;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.UserConvert;
import _blog.com._blog.repositories.ConnectionRepo;
import _blog.com._blog.repositories.UserRepository;

@Service
public class UserServ {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConnectionRepo connectionRepo;

    public UserServ(UserRepository userRepository, PasswordEncoder passwordEncoder, ConnectionRepo connectionRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.connectionRepo = connectionRepo;
    }

    @Transactional
    public User save(UserReq userReq) throws ProgramExeption {
        if (userRepository.existsByEmail(userReq.getEmail())) {
            throw new ProgramExeption(400, "email already exists try other one");
        }
        String userName = userReq.getUsername();
        if (userName != null && userRepository.existsByUsername(userName)) {
            throw new ProgramExeption(400, "username already exists try other one");

        }
        userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
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
        User user = UserConvert.convertToUser(userReq);
        user.setRole("USER");
        user.setStatus("ACTIVE");
        return userRepository.save(user);
    }

    public User login(UserReq userReq) throws ProgramExeption {
        User user = userRepository.findByEmail(userReq.getEmail())
                .orElseThrow(() -> new ProgramExeption(400, "user not found or password not correct"));
        if (passwordEncoder.matches(user.getPassword(), userReq.getPassword())) {
            throw new ProgramExeption(400, "user not found or password not correct");
        }
        return user;
    }

    public UserReq profile(String uuid, User me) throws Exception {
        Map<String, Object> info = userRepository.findUser(uuid);
        if (info == null) {
            throw new ProgramExeption(400, "User not found");
        }
        UserReq user = UserConvert.convertToUserReq(info);
        user.setMayAcount(user.getUuid() == me.getUuid());
        if (!user.isMayAcount()) {
            boolean isfollowing = connectionRepo.isfollowing(me.getId(), (Long) info.get("id"));
            user.setHasCon(isfollowing);
        }
        return user;
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
