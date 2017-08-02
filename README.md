# SimpleBus

Library with simple implementations of a command bus and a query bus.

## CommandBus

In a command bus you have to register handlers associated with commands. When you call the method `handle` with a command, the bus is responsible for executen the associated handler.
 
```java
class AddItemHandler {
    public void handleCommand(AddItemCommand command) {
        System.out.println("Command executed");
    }
}

// Create a CommandBus
CommandBus bus = new CommandBus();
// Create handler to run when the bus receives a command
AddItemHandler handler = new AddItemHandler();

// Add the handler to the bus associated with the command to be answered
bus.addHandler(AddItemCommand.class, handler::handleCommand);

// Create the command and handle by the bus
AddItemCommand command = new AddItemCommand(1);
bus.handle(command);
```

### Middleware

Middleware can be applied to CommandBus to perform actions before of after the handler is run.

```java
class LoggerMiddleware implements Middleware {
    @Override
    public <T> void execute(T command, Consumer<T> next) {
        System.out.println("Before the handler runs");
        next.accept(command);
        System.out.println("After the handler runs");
    }
}

LoggerMiddleware middleware = new LoggerMiddleware();
CommandBus bus = new CommandBus(middleware);
AddItemHandler handler = new AddItemHandler();

bus.addHandler(AddItemCommand.class, handler::handleCommand);

AddItemCommand command = new AddItemCommand(1);
bus.handle(command);
```

The expected result is:

```
Before the handler runs
Command executed
After the handler runs
```

## QueryBus

As opposite to CommandBus, a QueryBus executes a query and return a result.

```java
class FindItemHandler {
    public String handleQuery(FindItemQuery query) {
        return "This is the item found";
    }
}

// Create QueryBus
QueryBus bus = new QueryBus();

// Create handler to run when the bus receives a query
FindItemHandler findItemHandler = new FindItemHandler();

bus.addHandler(FindItemQuery.class, findItemHandler::handleQuery);

// Create the query and handle by the bus
FindItemQuery query = new FindItemQuery();
String result = bus.query(query, String.class);
```