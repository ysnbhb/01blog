package _blog.com._blog.model;

import lombok.Data;

@Data
public class CommentReq {
    private int userid;
    private String text;
}
