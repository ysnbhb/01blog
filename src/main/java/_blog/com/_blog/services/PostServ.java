package _blog.com._blog.services;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.dto.PostConvert;
import _blog.com._blog.dto.UserConvert;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.UserRepository;
import _blog.com._blog.utils.PostReq;
import _blog.com._blog.utils.Upload;

@Service
public class PostServ {
    private final PostRepositery postRepositery;
    private final UserRepository userRepository;

    public PostServ(PostRepositery PostRepositery, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.postRepositery = PostRepositery;
    }

    public Post save(PostReq postReq) throws UserExeption {
        User userOption = userRepository.findById(postReq.getUserid()).orElse(null);
        if (userOption == null) {
            return null;
        }
        postReq.setUser(UserConvert.convertToUserReq(userOption));
        var photo = postReq.getPhoto();
        if (photo != null) {

            try {
                postReq.setUrlPhot((Upload.saveImage(photo)));
                postReq.setTypePhoto("image");
            } catch (Exception e) {
                postReq.setUrlPhot((Upload.saveVideo(photo)));
                postReq.setTypePhoto("video");
            }
        }
        return postRepositery.save(PostConvert.convertToPost(postReq));
    }

}
