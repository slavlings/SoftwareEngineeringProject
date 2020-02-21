package model;

import model.exceptions.TeachRequestAlreadyGivenException;

import java.util.List;

/**
 * Represents the class director who directs a course,
 * can set the teaching requirements for it
 * and can produce a teaching request.
 */

public class ClassDirector extends Employee {
    private Course directedCourse;
    private TeachRequestMap teachRequestMap;

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
     * Sets the teaching requirements for course the class director directs
     */
    public void setCourseTeachRequirements(List<String> skill) {
        directedCourse.setTeachRequirements(skill);
    }

    /**
     * Adds teaching requests for the course the class director directs
     * @throws TeachRequestAlreadyGivenException a request for the given course has already been added
     */
    public void addTeachRequest() throws TeachRequestAlreadyGivenException {
        teachRequestMap.addTeachRequest(directedCourse);
    }



}
