package pl.skefb.springshop.exception;

public class EmailAlreadyTakenException extends RuntimeException{

    public EmailAlreadyTakenException (String message) {
        super(message);
    }
}
