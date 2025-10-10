package _blog.com._blog.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.Reaction;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
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

    public Map<String, Object> like(Long post_id, User user) throws ProgramExeption {
        if (post_id == 0) {
            throw new ProgramExeption(400, "Post not found");
        }
        Post post = postRepositery.findById(post_id).orElseThrow(() -> new ProgramExeption(400, "Post not found"));
        if (post.isHide()) {
            throw new ProgramExeption(400, "You can't react in this post");
        }
        boolean isLiked = reactionRepositery.isLiked(post_id, user.getId());
        if (isLiked) {
            reactionRepositery.deleteByPostIdAndUserId(post_id, user.getId());
        } else {
            Reaction reaction = new Reaction();
            reaction.setPost(post);
            reaction.setUser(user);
            reactionRepositery.save(reaction);
        }
        int countOfLikes = reactionRepositery.countOfLikedPost(post_id);
        return Map.of(
                "liked", !isLiked,
                "countOfLikes", countOfLikes);
    }
}
