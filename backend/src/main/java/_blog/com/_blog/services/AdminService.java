package _blog.com._blog.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.UserEntity;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.UserConvert;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.ReportRepostiry;
import _blog.com._blog.repositories.UserRepository;
import _blog.com._blog.utils.UserReq;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final PostRepositery postRepository;
    private final ReportRepostiry reportRepostiry;

    @Transactional
    public void delete(String uuid) throws ProgramExeption {
        UserEntity user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ProgramExeption(400, "User not found"));
        if (user.getRole() == "ADMIN") {
            throw new ProgramExeption(400, "you can't delete this admin user");
        }
        userRepository.delete(user);
    }

    public List<UserReq> getUsers(int offset, int limit) {
        List<UserReq> listuser = userRepository.findAllWithOffset(offset, limit).stream()
                .map(UserConvert::convertToUserReq)
                .toList();
        return listuser;
    }

    @Transactional
    public boolean hidePost(Long post_id) throws ProgramExeption {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new ProgramExeption(400, "Post not found"));
        postRepository.updateHideStatus(post_id, !post.isHide());
        return !post.isHide();
    }

    @Transactional
    public boolean banneUser(String uuid) throws ProgramExeption {
        UserEntity user = userRepository.findByUuid(uuid).orElseThrow(() -> new ProgramExeption(400, "User not found"));
        if (user.getRole().equals("ADMIN")) {
            throw new ProgramExeption(400, "User not found");
        }
        userRepository.updateUserStatus(uuid, user.getStatus().equals("BANNED") ? "ACTIVE" : "BANNED");
        user = userRepository.findByUuid(uuid).orElseThrow(() -> new ProgramExeption(400, "User not found"));
        return !user.getStatus().equals("BANNED");
    }


    public Map<String, Object> DashboardStats() {
        return Map.of("totalUsers", userRepository.count(), "userGrowth", userRepository.userGrowth(), "reportedUsers",
                reportRepostiry.CountReportsUser(), "reportedPosts", reportRepostiry.CountReportsPost());
    }
}
