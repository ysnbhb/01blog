package _blog.com._blog.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Connection;
import _blog.com._blog.Entity.UserEntity;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.repositories.ConnectionRepo;
import _blog.com._blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectionSrv {

    private final UserRepository userRepository;
    private final ConnectionRepo connectionRepo;
    private final NotifacationSer notifacationSer;

    @Transactional
    public Map<String, Object> follow(UserEntity user, String uuid) throws ProgramExeption {
        UserEntity following = userRepository.findByUuid(uuid).orElseThrow(() -> new ProgramExeption(400, "User not Found"));
        boolean isfollowing = connectionRepo.isfollowing(user.getId(), following.getId());
        if (isfollowing) {
            connectionRepo.deleteConnetion(user.getId(), following.getId());
            notifacationSer.deleteNotifaction(following.getId(), user.getId());
        } else {
            Connection connection = new Connection();
            connection.setFollower(user);
            connection.setFollowing(following);
            connectionRepo.save(connection);
            notifacationSer.saveNotifiction(user, following, "follow", "start follow you", null);
        }
        return Map.of(
                "isfollow", !isfollowing,
                "followor", connectionRepo.countOffollower(following.getId()) ,
                "follwining", connectionRepo.countOffollowing(following.getId()));
    }

}
