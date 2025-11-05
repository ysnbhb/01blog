package _blog.com._blog.dto;

import java.util.Map;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.utils.PostReq;
import _blog.com._blog.utils.UserReq;

public class PostConvert {
    public static Post convertToPost(PostReq postReq) {
        if (postReq == null)
            return null;
        Post post = new Post();
        post.setContent(postReq.getContent());
        post.setTitle(postReq.getTitle());
        return post;
    }

    public static PostReq convertToPostReq(Post postReq) {
        if (postReq == null)
            return null;
        PostReq post = new PostReq();
        post.setId(postReq.getId());
        post.setContent(postReq.getContent());
        post.setUser(UserConvert.convertToUserReq(postReq.getUser()));
        post.setCreatedAt(postReq.getCreatedAt());
        post.setTitle(postReq.getTitle());

        return post;
    }

    public static PostReq convertToPostReq(Map<String, Object> post) {
        PostReq postReq = new PostReq();
        postReq.setId(((Long) post.get("id")));
        postReq.setContent((String) post.get("content"));
        postReq.setNumOfcomment(((Number) post.get("total_comments")).intValue());
        postReq.setNumOflike(((Number) post.get("total_likes")).intValue());
        postReq.setIsliked((Boolean) post.get("isLiked"));
        postReq.setTitle((String) post.get("title"));
        postReq.setCreatedAt(((java.sql.Timestamp) post.get("createdAt")).toLocalDateTime());
        UserReq user = new UserReq();
        user.setUsername((String) post.get("username"));
        user.setName((String) post.get("name"));
        user.setLastName((String) post.get("lastName"));
        user.setUrlPhoto((String) post.get("userPhoto"));
        user.setUuid((String) post.get("uuid"));
        user.setRole((String) post.get("role"));
        postReq.setUser(user);
        return postReq;

    }

}
