package ExceptionHandling;

public abstract  class BaseException extends Exception {

    private String message;

    public BaseException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
