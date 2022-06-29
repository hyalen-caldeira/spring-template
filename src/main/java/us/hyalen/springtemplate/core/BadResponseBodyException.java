package us.hyalen.springtemplate.core;

public class BadResponseBodyException extends RuntimeException {
    public BadResponseBodyException(String message) {
        super(message);
    }
}
