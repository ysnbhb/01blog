package _blog.com._blog.services;

import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.Comment;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.repositories.CommentsRepositories;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.utils.CommentReq;

@Service
public class CommentsServ {
    private final PostRepositery postRepositery;
    private final CommentsRepositories commentsRepositories;

    public CommentsServ(PostRepositery postRepositery, CommentsRepositories commentsRepositories) {
        this.commentsRepositories = commentsRepositories;
        this.postRepositery = postRepositery;

    }

    public Comment save(CommentReq commentReq, User user) throws ProgramExeption {
        Post post = postRepositery.findById(commentReq.getPost_id())
                .orElseThrow(() -> new ProgramExeption(400, "post not found"));
        Comment comment = new Comment();
        comment.setContane(commentReq.getContent());
        comment.setPost(post);
        comment.setUser(user);
        return commentsRepositories.save(comment);
    }
}
