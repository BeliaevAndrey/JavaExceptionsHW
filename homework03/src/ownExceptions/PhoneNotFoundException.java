package ownExceptions;

public class PhoneNotFoundException extends IllegalStateException {

    public PhoneNotFoundException() {
        super("Phone number filed parse exception.");
    }

    public PhoneNotFoundException(String msg) {
        super(msg);
    }
}
