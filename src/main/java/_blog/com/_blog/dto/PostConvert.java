package _blog.com._blog.dto;

import _blog.com._blog.Entity.Post;
import _blog.com._blog.utils.PostReq;

public class PostConvert {
    public static Post convertToPost(PostReq postReq) {
        Post post = new Post();
        post.setContent(postReq.getText());
        post.setUrlPhoto(postReq.getUrlPhot());
        post.setUser(UserConvert.convertToUser(postReq.getUser()));
        post.setTypePhoto(postReq.getTypePhoto());
        return post;
    }

}
