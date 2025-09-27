package _blog.com._blog.Exception;

public class UserExeption extends Exception {
    private int status;
    private String contan;

    public UserExeption(int status, String contan) {
        this.status = status;
        this.contan = contan;
    }

    public int getStatus() {
        return status;
    }

    public String getContan() {
        return contan;
    }
}
