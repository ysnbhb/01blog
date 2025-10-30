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
import _blog.com._blog.repositories.UserRepository;
import _blog.com._blog.utils.UserReq;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final PostRepositery postRepository;

    public AdminService(UserRepository userRepository, PostRepositery postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void delete(String uuid) throws ProgramExeption {
        UserEntity user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ProgramExeption(400, "User not found"));
        postRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }

    public List<UserReq> getUsers(int offset) {
        List<UserReq> listuser = userRepository.findAllWithOffset(offset).stream().map(UserConvert::convertToUserReq)
                .toList();
        return listuser;
    }
    @Transactional
    public boolean hidePost(Long post_id) throws ProgramExeption {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new ProgramExeption(400, "Post not found"));
        postRepository.updateHideStatus(post_id, post.isHide());
        return !post.isHide();
    }
    @Transactional
    public boolean banneUser(String uuid) throws ProgramExeption {
        UserEntity user = userRepository.findByUuid(uuid).orElseThrow(() -> new ProgramExeption(400, "User not found"));
        userRepository.updateUserStatus(uuid, user.getStatus() != "BANNED" ? "BANNED" : "ACTIVE");
        return user.getStatus() != "BANNED";
    }

    public List<Map<String, Object>> getHidePost(Long userid, int offset) throws ProgramExeption {
        try {
            return postRepository.getPosts(userid, offset, true);
        } catch (Exception e) {
            throw new ProgramExeption(500, "some unexpacte error");
        }
    }
}
