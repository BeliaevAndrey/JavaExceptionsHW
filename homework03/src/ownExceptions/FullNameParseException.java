package ownExceptions;

public class FullNameParseException extends IllegalStateException {

    public FullNameParseException() {
        super("Full-name validation exception");
    }

    public FullNameParseException(String msg) {
        super("Full-name validation exception: " + msg);
    }
}
