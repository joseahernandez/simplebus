package joseahernandez.simplebus;

import java.util.*;
import java.util.function.Consumer;

public class CommandBus {
    private Map<Class<?>, Consumer<?>> handlers;
    private List<Middleware> middlewares;

    public CommandBus(Middleware... middleware) {
        this.handlers = new HashMap<>();
        this.middlewares = Arrays.asList(middleware);
    }

    public <T> void addHandler(Class<T> command, Consumer<T> commandHandler) {
        Consumer<T> lastCallable = commandHandler;

        for (Middleware middleware : middlewares) {
            final Consumer<T> currentLastCallable = lastCallable;
            lastCallable = (c) -> middleware.execute(c, currentLastCallable);
        }

        handlers.put(command, lastCallable);
    }

    public <T> void deleteHandler(Class<T> command) {
        handlers.remove(command);
    }

    @SuppressWarnings("unchecked")
    public <T> void handle(T command) {
        if (!handlers.containsKey(command.getClass())) {
            throw new HandlerNotFoundException("No handler has defined for command " + command.getClass().getCanonicalName());
        }

        ((Consumer<T>) handlers.get(command.getClass())).accept(command);
    }
}
