package model;

import com.fasterxml.jackson.annotation.*;

/**
 * Base Employee class.
 */
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Employee {

    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Employee(@JsonProperty("name") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
