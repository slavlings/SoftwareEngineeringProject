package model;

import model.exceptions.NonExistentTeachRequestException;

/**
 * Represents a PTTDirector.
 * Has functionality for approving teaching requests.
 */
public class PTTDirector extends Employee {

    private TeachRequestMap teachRequestMap;

    /**
     * Constructor.
     * @param name name of director
     * @param teachRequestMap holds teaching requests to be approved by director
     */
    public PTTDirector(String name, TeachRequestMap teachRequestMap) {
        super(name);
        this.teachRequestMap = teachRequestMap;
    }

    /**
     * Attempts to approve a teaching request for the given course.
     * If unsuccessful prints the stack trace of any caught exceptions
     * or throws a NonExistentTeachRequestException.
     * @param course given course
     * @throws NonExistentTeachRequestException
     */
    public void approveTeachRequest(Course course) throws NonExistentTeachRequestException {
        teachRequestMap.approveTeachRequest(course);
    }
}
