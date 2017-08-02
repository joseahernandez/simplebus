package joseahernandez.simplebus.fixtures;

public class FindItemQuery {
    private int id;

    public FindItemQuery(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FindItemQuery that = (FindItemQuery) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
