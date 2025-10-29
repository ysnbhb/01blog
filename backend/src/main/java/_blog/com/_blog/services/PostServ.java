package _blog.com._blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

// import _blog.com._blog.Entity.Image;
import _blog.com._blog.Entity.Post;
import _blog.com._blog.Entity.User;
import _blog.com._blog.Exception.ProgramExeption;
import _blog.com._blog.dto.ImageCovert;
import _blog.com._blog.dto.PostConvert;
import _blog.com._blog.repositories.CommentsRepositories;
import _blog.com._blog.repositories.ImageRepo;
import _blog.com._blog.repositories.PostRepositery;
import _blog.com._blog.repositories.ReactionRepo;
import _blog.com._blog.repositories.ReportRepostiry;
import _blog.com._blog.utils.ImageReq;
import _blog.com._blog.utils.PostReq;
import _blog.com._blog.utils.Upload;

@Service
public class PostServ {
    private final PostRepositery postRepositery;
    private final NotifacationSer notifacationSer;
    private final ReactionRepo reactionRepo;
    private final CommentsRepositories commentsRepositories;
    private final ReportRepostiry reportRepostiry;
    private final ImageRepo imageRepo;

    public PostServ(PostRepositery PostRepositery, NotifacationSer notifacationSer, ReactionRepo reactionRepo,
            CommentsRepositories commentsRepositories, ReportRepostiry reportRepostiry, ImageRepo imageRepo) {
        this.postRepositery = PostRepositery;
        this.notifacationSer = notifacationSer;
        this.reactionRepo = reactionRepo;
        this.commentsRepositories = commentsRepositories;
        this.reportRepostiry = reportRepostiry;
        this.imageRepo = imageRepo;
    }

    @Transactional
    public Post save(PostReq postReq, User user) throws ProgramExeption {
        var photo = postReq.getPhoto();
        if (photo != null && photo.length > 0) {
            if (postReq.getImages() == null) {
                postReq.setImages(new ArrayList<>());
            }

            for (MultipartFile file : photo) {
                if (file.getSize() > 50 * 1024 * 1024) {
                    throw new ProgramExeption(400, "File size must be less than 50MB");
                }

                String url;
                String type;

                try {
                    Upload.isRealPhoto(file);
                    url = Upload.saveImage(file);
                    type = "image";
                } catch (Exception e) {
                    if (Upload.isLikelyVideo(file)) {
                        url = Upload.saveVideo(file);
                        type = "video";
                    } else {
                        throw new ProgramExeption(400, "File is neither a valid image nor a valid video");
                    }
                }
                postReq.getImages().add(new ImageReq(url, type));
            }
        }

        Post post = PostConvert.convertToPost(postReq);
        post.setUser(user);
        post = postRepositery.save(post);
        for (ImageReq img : postReq.getImages()) {
            imageRepo.save(ImageCovert.convertToImageEntity(img, post));
        }
        notifacationSer.setNotification(user, post);
        return post;
    }

    @Transactional
    public void delete(long Postid, User user) throws ProgramExeption {
        Post Post = postRepositery.findById(Postid)
                .orElseThrow(() -> new ProgramExeption(400, "Post not found"));

        if ((Post.getUser().getId() == user.getId() && !Post.isHide()) || user.getRole() == "ADMIN") {
            reactionRepo.deleteByPost(Postid);
            notifacationSer.deleteNotifactionByPostid(Postid);
            commentsRepositories.deleteByPostid(Postid);
            reportRepostiry.deleteByPost(Postid);
            postRepositery.deleteById(Postid);
        } else {
            throw new ProgramExeption(400, "you can't delet this post");
        }
    }

    public List<PostReq> getPosts(Long userId, int offset) throws ProgramExeption {
        return postRepositery.getPosts(userId, offset, false)
                .stream()
                .map((post) -> {
                    PostReq postReq = PostConvert.convertToPostReq(post);
                    List<ImageReq> images = imageRepo.findImgesByPostId(postReq.getId())
                            .stream()
                            .map(ImageCovert::convertToImageUtil)
                            .toList();
                    postReq.setImages(images);
                    return postReq;
                })
                .toList();
    }

    public List<PostReq> getUserPosts(Long userid, int offset, String uuid) throws ProgramExeption {
        return postRepositery.getUserPosts(userid, offset, false, uuid)
                .stream()
                .map((post) -> {
                    PostReq postReq = PostConvert.convertToPostReq(post);
                    List<ImageReq> images = imageRepo.findImgesByPostId(postReq.getId())
                            .stream()
                            .map(ImageCovert::convertToImageUtil)
                            .toList();
                    postReq.setImages(images);
                    return postReq;
                })
                .toList();
    }

    public Map<String, Object> getPost(Long userid, Long postId) throws ProgramExeption {
        return postRepositery.getPost(userid, postId, false);
    }

}
