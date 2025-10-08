package _blog.com._blog.dto;

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
        // userReq.setStatus(user.getStatus()); 

        return userReq;
    }

    public static User convertToUser(UserReq userReq) {
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
