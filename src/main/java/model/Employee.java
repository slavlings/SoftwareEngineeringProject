package model;

/**
 * Base Employee class.
 */
public abstract class Employee {

    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
