package pl.skefb.springshop.exception;

public class EmailAlreadyConfirmedException extends RuntimeException{

    public EmailAlreadyConfirmedException (String message) {
        super(message);
    }
}
