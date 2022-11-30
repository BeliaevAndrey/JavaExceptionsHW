package ownExceptions;

public class UnbornPersonException extends IllegalStateException {

    public UnbornPersonException() {
        super("Future Birthday Exception");
    }

    public UnbornPersonException(String msg) {
        super(msg);
    }
}
