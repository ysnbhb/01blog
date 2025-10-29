package _blog.com._blog.dto;

import _blog.com._blog.Entity.Image;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.utils.ImageReq;

public class ImageCovert {
    static public ImageReq convertToImageUtil(Image image) {
        return new ImageReq(image.getUrl(), image.getType());
    }

    static public Image convertToImageEntity(ImageReq image, Post post) {
        Image imgae = new Image();
        imgae.setUrl(image.getUrl());
        imgae.setType(image.getType());
        imgae.setPost(post);
        return imgae;
    }
}
