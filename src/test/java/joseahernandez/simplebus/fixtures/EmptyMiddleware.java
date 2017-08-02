package joseahernandez.simplebus.fixtures;

import joseahernandez.simplebus.Middleware;

import java.util.function.Consumer;

public class EmptyMiddleware implements Middleware {
    @Override
    public <T> void execute(T command, Consumer<T> next) {
        next.accept(command);
    }
}
