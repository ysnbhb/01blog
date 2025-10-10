package _blog.com._blog.dto;

import java.util.List;
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
        post.setUrlPhoto(postReq.getUrlPhot());
        post.setTypePhoto(postReq.getTypePhoto());
        return post;
    }

    public static PostReq convertToPostReq(Post postReq) {
        if (postReq == null)
            return null;
        PostReq post = new PostReq();
        post.setContent(postReq.getContent());
        post.setUrlPhot(postReq.getUrlPhoto());
        post.setUser(UserConvert.convertToUserReq(postReq.getUser()));
        post.setTypePhoto(postReq.getTypePhoto());
        return post;
    }

    public static List<PostReq> convertToPostReq(List<Map<String, Object>> posList) {
        return posList.stream().map((post) -> {
            PostReq postReq = new PostReq();
            postReq.setId(((Long) post.get("id")));
            postReq.setContent((String) post.get("content"));
            postReq.setUrlPhot((String) post.get("urlPhoto"));
            postReq.setTypePhoto((String) post.get("typePhoto"));
            postReq.setNumOfcomment(((Number) post.get("total_comments")).intValue());
            postReq.setNumOflike(((Number) post.get("total_likes")).intValue());
            postReq.setIsliked((Boolean) post.get("isLiked"));
            postReq.setCreatedAt(((java.sql.Timestamp) post.get("createdAt")).toLocalDateTime());
            UserReq user = new UserReq();
            user.setUsername((String) post.get("username"));
            user.setName((String) post.get("name"));
            user.setLastName((String) post.get("lastName"));
            user.setUrlPhoto((String) post.get("userPhoto"));
            user.setUuid((String) post.get("uuid"));
            postReq.setUser(user);
            return postReq;
        }).toList();
    }

}
