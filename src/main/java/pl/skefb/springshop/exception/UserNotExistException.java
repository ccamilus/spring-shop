package pl.skefb.springshop.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException (String message) {
        super(message);
    }
}
