package joseahernandez.simplebus;

import joseahernandez.simplebus.fixtures.AddItemCommand;
import joseahernandez.simplebus.fixtures.EmptyMiddleware;
import org.junit.Test;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class CommandBusTest {

    @Test
    public void executeHandlerForCommand() {
        CommandBus commandBus = new CommandBus();
        AddItemCommand command = new AddItemCommand(1);

        Consumer<AddItemCommand> consumer = mock(Consumer.class);
        commandBus.addHandler(AddItemCommand.class, consumer);

        commandBus.handle(command);

        verify(consumer).accept(command);
    }

    @Test(expected = HandlerNotFoundException.class)
    public void commandWithoutHandler() {
        CommandBus commandBus = new CommandBus();
        AddItemCommand command = new AddItemCommand(1);

        commandBus.handle(command);
    }

    @Test
    public void executeMiddlewareBeforeCommandHandler() {
        EmptyMiddleware middleware = mock(EmptyMiddleware.class);
        CommandBus commandBus = new CommandBus(middleware);
        AddItemCommand command = new AddItemCommand(1);

        Consumer<AddItemCommand> consumer = mock(Consumer.class);
        doCallRealMethod().when(middleware).execute(command, consumer);
        commandBus.addHandler(AddItemCommand.class, consumer);
        commandBus.handle(command);

        verify(middleware).execute(command, consumer);
        verify(consumer).accept(command);
    }

    @Test(expected = HandlerNotFoundException.class)
    public void deleteHandler() {
        CommandBus commandBus = new CommandBus();
        AddItemCommand command = new AddItemCommand(1);

        Consumer<AddItemCommand> consumer = mock(Consumer.class);
        commandBus.addHandler(AddItemCommand.class, consumer);

        commandBus.handle(command);

        verify(consumer).accept(command);

        commandBus.deleteHandler(AddItemCommand.class);
        commandBus.handle(command);
    }
}
