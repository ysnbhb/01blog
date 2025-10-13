package _blog.com._blog.dto;

import java.util.Map;

import _blog.com._blog.Entity.User;
import _blog.com._blog.utils.UserReq;

public class UserConvert {

    public static UserReq convertToUserReq(User user) {
        if (user == null) {
            return null;
        }
        UserReq userReq = new UserReq();
        userReq.setDateOfBirth(user.getDateOfBirth());
        userReq.setEmail(user.getEmail());
        userReq.setUsername(user.getUsername());
        userReq.setName(user.getName());
        userReq.setLastName(user.getLastName());
        userReq.setUrlPhoto(user.getUrlPhoto());
        userReq.setUuid(user.getUuid());
        userReq.setRole(user.getRole());
        userReq.setStutes(user.getStatus());
        return userReq;
    }

    public static UserReq convertToUserReq(Map<String, Object> info) {
        if (info == null) {
            return null;
        }
        UserReq userReq = new UserReq();
        userReq.setDateOfBirth((String) info.get("dateOfBirth"));
        userReq.setUsername((String) info.get("username"));
        userReq.setName((String) info.get("name"));
        userReq.setLastName((String) info.get("lastName"));
        userReq.setUrlPhoto((String) info.get("urlPhoto"));
        userReq.setUuid((String) info.get("uuid"));
        userReq.setRole((String) info.get("role"));
        userReq.setFollowers((Integer) info.get("follower"));
        userReq.setFollowing((Integer) info.get("following"));
        return userReq;
    }

    public static User convertToUser(UserReq userReq) {
        if (userReq == null) {
            return null;
        }
        User user = new User();
        user.setEmail(userReq.getEmail());
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setDateOfBirth(userReq.getDateOfBirth());
        user.setName(userReq.getName());
        user.setLastName(userReq.getLastName());
        user.setUrlPhoto(userReq.getUrlPhoto());
        return user;
    }
}
