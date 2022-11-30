package ownExceptions;

public class GenParseException extends IllegalStateException {

    public GenParseException() {
        super("Gender validation exception");
    }

    public GenParseException(String msg) {
        super(msg);
    }
}
