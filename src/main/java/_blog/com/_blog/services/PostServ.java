package _blog.com._blog.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.PostConvert;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.utils.PostReq;
import _blog.com._blog.utils.Upload;

@Service
public class PostServ {
    private final PostRepositery postRepositery;

    public PostServ(PostRepositery PostRepositery) {
        this.postRepositery = PostRepositery;
    }

    public Post save(PostReq postReq, User user) throws ProgramExeption {
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
        return postRepositery.save(post);
    }

    public void delete(long Postid, User user) throws ProgramExeption {
        User ownrPost = postRepositery.findById(Postid)
                .map(Post::getUser)
                .orElseThrow(() -> new ProgramExeption(404, "Post not found"));

        if (ownrPost.getId() == user.getId() || user.getRole() == "ADMIN") {
            postRepositery.deleteById(Postid);
        } else {
            throw new ProgramExeption(400, "you can't delet this post");
        }
    }

    public List<Map<String, Object>> getPost(Long userid, int offset) throws ProgramExeption {
        try {
            return postRepositery.getPosts(userid, offset, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProgramExeption(500, "some unexpacte error");
        }
    }

}
