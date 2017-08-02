package joseahernandez.simplebus;

import java.util.function.Consumer;

public interface Middleware {

    <T> void execute(T command, Consumer<T> next);
}
