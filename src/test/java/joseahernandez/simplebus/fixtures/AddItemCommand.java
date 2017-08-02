package joseahernandez.simplebus.fixtures;

public class AddItemCommand {
    private int id;

    public AddItemCommand(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddItemCommand that = (AddItemCommand) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
