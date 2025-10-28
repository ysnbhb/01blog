package _blog.com._blog.dto;

import java.util.List;
import java.util.Map;

import _blog.com._blog.Entity.Comment;
import _blog.com._blog.utils.CommentReq;
import _blog.com._blog.utils.UserReq;

public class CommentConert {
    public static CommentReq convertToPost(Comment comment) {
        if (comment == null)
            return null;
        CommentReq post = new CommentReq();
        post.setContent(comment.getContent());
        post.setId(comment.getId());
        post.setUser(UserConvert.convertToUserReq(comment.getUser()));

        return post;
    }

    public static List<CommentReq> convertToCommettReq(List<Map<String, Object>> commList) {
        return commList.stream().map((post) -> {
            CommentReq commentReq = new CommentReq();
            commentReq.setId(((Long) post.get("id")));
            commentReq.setContent((String) post.get("content"));
            commentReq.setNumOflike(((Number) post.get("total_likes")).intValue());
            commentReq.setIsliked((Boolean) post.get("isLiked"));
            commentReq.setCreatedAt(((java.sql.Timestamp) post.get("createdAt")).toLocalDateTime());
            UserReq user = new UserReq();
            user.setUsername((String) post.get("username"));
            user.setName((String) post.get("name"));
            user.setLastName((String) post.get("lastName"));
            user.setUrlPhoto((String) post.get("userPhoto"));
            user.setUuid((String) post.get("uuid"));
            commentReq.setUser(user);
            return commentReq;
        }).toList();
    }
}
