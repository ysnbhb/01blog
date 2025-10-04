package _blog.com._blog.services;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.dto.PostConvert;
// import _blog.com._blog.dto.UserConvert;
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
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserExeption(404, "User not found"));
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
        Post post = PostConvert.convertToPost(postReq);
        post.setUser(user);
        System.out.println(user.toString());
        return postRepositery.save(post);
    }

    public void delete(long Postid, long userId) throws UserExeption {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserExeption(404, "User not found"));
        User ownrPost = postRepositery.findById(Postid)
                .map(Post::getUser)
                .orElseThrow(() -> new UserExeption(404, "Post not found"));

        ;
        if (user.equals(ownrPost)) {
            postRepositery.deleteById(Postid);
        } else {
            throw new UserExeption(400, "you can't delet this post");
        }
    }

}
