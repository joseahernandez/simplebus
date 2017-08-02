package joseahernandez.simplebus;

public class HandlerNotFoundException extends RuntimeException {

    public HandlerNotFoundException(String message) {
        super(message);
    }
}
