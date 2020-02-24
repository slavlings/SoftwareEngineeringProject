package model;

import com.fasterxml.jackson.annotation.*;

/**
 * Base Employee class.
 */
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Employee.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class Employee {

    private static int maxID = 0;
    private final String name;
    private final int id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Employee(@JsonProperty("name") String name, @JsonProperty("id") int id) {
        this.name = name;
        this.id = id;
    }

    public Employee(String name) {
        this(name,++maxID);
    }

    @Override
    public String toString() {
        return name;
    }

    public int getMaxID() {
        return maxID;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
