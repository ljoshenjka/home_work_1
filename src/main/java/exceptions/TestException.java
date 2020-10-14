package exceptions;

public class TestException extends RuntimeException {

    public TestException(String message) {
        super(message);
    }

    public TestException(String message, Throwable cause) {
        super(message + "\n" + cause.getMessage(), cause);
    }
}
