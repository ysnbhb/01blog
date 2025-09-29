package _blog.com._blog.Exception;

public class UserExeption extends Exception {
    private int status;
    private String message;

    public UserExeption(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
