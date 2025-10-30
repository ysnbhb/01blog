package _blog.com._blog.services;

import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Notifacation;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.repositories.ConnectionRepo;
import _blog.com._blog.repositories.NotifacationRepo;
import _blog.com._blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotifacationSer {
    private final NotifacationRepo notifacationRepo;
    private final ConnectionRepo connectionRepo;
    private final UserRepository userRepository;

    @Transactional
    public void saveNotifiction(User from, User user, String type, String content, Post post) {
        Notifacation notifacation = new Notifacation();
        notifacation.setContent(content);
        notifacation.setFrom(from);
        notifacation.setType(type);
        notifacation.setUser(user);
        notifacation.setPost(post);
        notifacationRepo.save(notifacation);
    }

    public void deleteNotifaction(Long userid, Long fromid) {
        notifacationRepo.deleteNotifaction(userid, fromid);
    }

    public void deleteNotifactionByPostid(Long postid) {
        notifacationRepo.deleteNotifactionByPostid(postid);
    }

    public void raedNotif(Long nofifId, Long userid) throws ProgramExeption {
        Notifacation notif = notifacationRepo.findById(nofifId).orElse(null);
        if (notif != null && notif.getUser().getId().equals(userid)) {
            notifacationRepo.updateRead(userid);
        } else {
            throw new ProgramExeption(403, "You are not authorized to read this notification");
        }
    }

    @Async
    @Transactional
    public void setNotification(User sender, Post post) {
        int offset = 0;
        List<Long> listOfIds;

        do {
            listOfIds = connectionRepo.getFollowes(sender.getId(), offset);
            if (listOfIds == null || listOfIds.isEmpty())
                break;

            for (Long userId : listOfIds) {
                User follower = userRepository.findById(userId).orElse(null);
                if (follower != null) {
                    saveNotifiction(sender, follower, "post", "posted a new blog", post);
                }
            }

            offset += 10;
        } while (listOfIds.size() == 10);
    }

    public List<Map<String, Object>> findAllNotifactions(Long userid) {
        List<Map<String, Object>> notfication = notifacationRepo.findAllNotifactions(userid);
        return notfication;
    }

    public int countNotif(Long userid) {
        return notifacationRepo.count(userid);
    }
}
