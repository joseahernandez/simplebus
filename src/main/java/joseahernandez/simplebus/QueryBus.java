package joseahernandez.simplebus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class QueryBus {
    private Map<Class<?>, Function<?, ?>> handlers;

    public QueryBus() {
        this.handlers = new HashMap<>();
    }

    public <T, R> void addHandler(Class<T> query, Function<T, R> queryHandler) {
        handlers.put(query, queryHandler);
    }

    public <T> void deleteHandler(Class<T> query) {
        handlers.remove(query);
    }

    @SuppressWarnings("unchecked")
    public <T, R> R query(T query, Class<R> returnType) {
        if (!handlers.containsKey(query.getClass())) {
            throw new HandlerNotFoundException("No handler has defined for query " + query.getClass().getCanonicalName());
        }

        return ((Function<T, R>) handlers.get(query.getClass())).apply(query);
    }
}
