package _blog.com._blog.utils;

import lombok.Data;

@Data
public class PostRes {
    private UserReq user;
    private String text;
    private String urlPhot;
    private String typePhoto;
    private int numOflike;
    private int numOfcomment;
    private boolean isliked;
}
