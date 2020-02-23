package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import model.exceptions.NoProposedTeacherException;
import model.exceptions.NonExistentTeachRequestException;
import model.exceptions.TeacherNotCompletedTrainingException;

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
     * Try to approve the teaching request, throws exceptions if unsuccessful.
     * @param course given course
     * @throws NonExistentTeachRequestException the teaching request does not exist
     * @throws NoProposedTeacherException there is no proposed teacher
     * @throws TeacherNotCompletedTrainingException the proposed teacher hasn't completed the required training
     */
    public void approveTeachRequest(Course course) throws NonExistentTeachRequestException, NoProposedTeacherException, TeacherNotCompletedTrainingException {
        teachRequestMap.approveTeachRequest(course);
    }
}
