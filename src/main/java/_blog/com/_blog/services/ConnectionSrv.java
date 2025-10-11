package _blog.com._blog.services;

import java.util.Map;

// import java.sql.Connection;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Connection;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.repositories.ConnectionRepo;
import _blog.com._blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectionSrv {

    private final UserRepository userRepository;
    private final ConnectionRepo connectionRepo;

    public Map<String, Object> follow(User user, String uuid) throws ProgramExeption {
        User following = userRepository.findByUuid(uuid).orElseThrow(() -> new ProgramExeption(400, "User not Found"));
        boolean isfollowing = connectionRepo.isfollowing(user.getId(), following.getId());
        if (isfollowing) {
            connectionRepo.deleteConnetion(user.getId(), following.getId());
        } else {
            Connection connection = new Connection();
            connection.setFollower(user);
            connection.setFollowing(following);
            connectionRepo.save(connection);
        }
        return Map.of(
                "isfollow", !isfollowing,
                "countOfFollwinf", connectionRepo.countOffolloewr(following.getId()));
    }

}
