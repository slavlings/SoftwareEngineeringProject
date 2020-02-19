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
     * If unsuccessful prints the stack trace of any caught exceptions.
     * @param course given course
     */
    public void approveTeachRequest(Course course) {
        //try to approve the request and print any exception trace if unsuccessful
        try {
            teachRequestMap.approveTeachRequest(course);
        } catch (NonExistentTeachRequestException e) {
            e.printStackTrace();
        }
    }
}
