package _blog.com._blog.dto;

import _blog.com._blog.Entity.Comment;
import _blog.com._blog.utils.CommentReq;

public class CommentConert {
     public static CommentReq convertToPost(Comment comment) {
        if (comment == null)
            return null;
        CommentReq post = new CommentReq();
        post.setContent(comment.getContane());
        post.setId(comment.getId());
        post.setUser(UserConvert.convertToUserReq(comment.getUser()));
        
        return post;
    }
}
