package _blog.com._blog.utils;

import lombok.Data;

@Data
public class CommentRes {
    private UserRes user;
    private String text;
    private int numOflike;
    private boolean isliked;
}
