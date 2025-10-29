package _blog.com._blog.utils;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageReq {
    private String url;
    private String type;
}
