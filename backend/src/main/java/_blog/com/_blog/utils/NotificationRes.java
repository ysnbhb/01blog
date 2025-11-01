package _blog.com._blog.utils;

import java.time.LocalDateTime;


public interface NotificationRes {
    Long getId();

    String getType();

    String getContent();

    Long getPostId();

    String getToUsername();

    String getToUuid();

    String getFromUsername();

    String getFromUuid();

    String getUrl();

    LocalDateTime getCreatedAt();

    boolean getIsRead();
}