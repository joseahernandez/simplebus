package joseahernandez.simplebus;

import joseahernandez.simplebus.fixtures.FindItemQuery;
import org.junit.Assert;
import org.junit.Test;
import java.util.function.Function;
import static org.mockito.Mockito.*;

public class QueryBusTest {

    @Test
    public void executeHandleForQuery() {
        QueryBus queryBus = new QueryBus();
        FindItemQuery query = new FindItemQuery(1);

        Function<FindItemQuery, String> function = mock(Function.class);
        when(function.apply(query)).thenReturn("Apple");
        queryBus.addHandler(FindItemQuery.class, function);

        String result = queryBus.query(query, String.class);

        verify(function).apply(query);
        Assert.assertEquals("Apple", result);
    }

    @Test(expected = HandlerNotFoundException.class)
    public void queryWithoutHandler() {
        QueryBus queryBus = new QueryBus();
        FindItemQuery query = new FindItemQuery(1);

        queryBus.query(query, String.class);
    }

    @Test(expected = HandlerNotFoundException.class)
    public void deleteHandler() {
        QueryBus queryBus = new QueryBus();
        FindItemQuery query = new FindItemQuery(1);

        Function<FindItemQuery, String> function = mock(Function.class);
        when(function.apply(query)).thenReturn("Apple");
        queryBus.addHandler(FindItemQuery.class, function);

        String result = queryBus.query(query, String.class);
        verify(function).apply(query);
        Assert.assertEquals("Apple", result);

        queryBus.deleteHandler(FindItemQuery.class);
        queryBus.query(query, String.class);
    }
}
