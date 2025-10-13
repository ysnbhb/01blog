package _blog.com._blog.Exception;

public class ProgramExeption extends Exception {
    private int status;
    private String message;

    public ProgramExeption(int status, String message) {
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
