package model;

import com.fasterxml.jackson.annotation.*;
import model.exceptions.TeachRequestAlreadyGivenException;

import java.util.List;

/**
 * Represents the class director who directs a course,
 * can set the teaching requirements for it
 * and can produce a teaching request.
 */
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ClassDirector extends Employee {
    private Course directedCourse;
    private TeachRequestMap teachRequestMap;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ClassDirector(@JsonProperty("name") String name,@JsonProperty("directedCourse") Course directedCourse,@JsonProperty("teachRequestMap") TeachRequestMap teachRequestMap) {
        super(name);
        this.directedCourse = directedCourse;
        this.teachRequestMap = teachRequestMap;
    }

    /**
     * Constructor
     *  @param name name of class director
     *  @param teachRequestMap holds teaching requests
     */
    public ClassDirector(String name, TeachRequestMap teachRequestMap) {
        super(name);
        this.teachRequestMap = teachRequestMap;
    }

    /**
     * Sets the teaching requirements(teacher skills) for the course the class director directs
     */
    public void setCourseTeachRequirements(List<String> skills) {
        directedCourse.setTeachRequirements(skills);
    }

    /**
     * Adds teaching requests for the course the class director directs
     * @throws TeachRequestAlreadyGivenException a request for the given course has already been added
     */
    public void addTeachRequest() throws TeachRequestAlreadyGivenException {
        teachRequestMap.addTeachRequest(directedCourse);
    }

    public void setDirectedCourse(Course directedCourse) {
        this.directedCourse = directedCourse;
    }
}
