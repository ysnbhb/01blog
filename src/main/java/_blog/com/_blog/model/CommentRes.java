package _blog.com._blog.model;

import lombok.Data;

@Data
public class CommentRes {
    private UserRes user;
    private String text;
    private int numOflike;
    private boolean isliked;
}
