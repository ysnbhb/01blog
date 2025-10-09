package _blog.com._blog.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.dto.UserConvert;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.UserRepository;
import _blog.com._blog.utils.UserReq;
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
    public void delete(String uuid) throws UserExeption {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserExeption(400, "User not found"));
        postRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }

    public List<UserReq> getUsers(int offset) {
        List<UserReq> listuser = userRepository.findAllWithOffset(offset).stream().map(UserConvert::convertToUserReq)
                .toList();
        return listuser;
    }

    public boolean hidePost(Long post_id) throws UserExeption {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new UserExeption(400, "Post not found"));
        postRepository.updateHideStatus(post_id, post.isHide());
        return !post.isHide();
    }

    public List<Map<String, Object>> getPost(Long userid, int offset) throws UserExeption {
        try {
            return postRepository.getPosts(userid, offset);
        } catch (Exception e) {
            throw new UserExeption(500, "some unexpacte error");
        }
    }
}
