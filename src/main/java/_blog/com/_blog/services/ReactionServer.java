package _blog.com._blog.services;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.Reaction;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.UserExeption;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.ReactionRepo;

@Service
public class ReactionServer {
    private final PostRepositery postRepositery;
    private final ReactionRepo reactionRepositery;

    public ReactionServer(PostRepositery PostRepositery, ReactionRepo reactionRepositery) {
        this.postRepositery = PostRepositery;
        this.reactionRepositery = reactionRepositery;
    }

    public void like(Long post_id, User user) throws UserExeption {
        if (post_id == 0) {
            return;
        }
        Post post = postRepositery.findById(post_id).orElseThrow(() -> new UserExeption(400, "Post not found"));
        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setUser(user);
        reactionRepositery.save(reaction);
    }
}
