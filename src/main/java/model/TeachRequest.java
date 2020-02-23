package model;

import com.fasterxml.jackson.annotation.*;
import model.exceptions.NoProposedTeacherException;
import model.exceptions.TeacherNotCompletedTrainingException;

/**
 * Represents a teaching request, which corresponds to a particular course and may have a proposed teacher.
 */
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = TeachRequest.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TeachRequest {
    private Course requestedCourse;
    private Teacher proposedTeacher;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TeachRequest(@JsonProperty("requestedCourse") Course requestedCourse,@JsonProperty("proposedTeacher") Teacher proposedTeacher) {
        this.requestedCourse = requestedCourse;
        this.proposedTeacher = proposedTeacher;
    }

    /**
     * Constructor.
     * @param requestedCourse the course the request is for
     */
    public TeachRequest(Course requestedCourse) {
        this.requestedCourse = requestedCourse;
        this.proposedTeacher = null;
    }

    /**
     * Add the given teacher to the request as a proposed teacher.
     * @param teacher given teacher
     */
    public void proposeTeacher(Teacher teacher) {
        proposedTeacher = teacher;
    }

    /**
     * Try to approve the teaching request, throws exceptions if unsuccessful.
     * @throws NoProposedTeacherException there is no proposed teacher
     * @throws TeacherNotCompletedTrainingException the proposed teacher hasn't completed the required training
     */
    public void approve() throws NoProposedTeacherException, TeacherNotCompletedTrainingException {
        if (proposedTeacher == null) {
            //there is no proposed teacher
            throw new NoProposedTeacherException();
        } else if (!proposedTeacher.completedTraining(requestedCourse)) {
            //the proposed teacher hasn't completed the required training
            throw new TeacherNotCompletedTrainingException();
        } else {
            //approve the request
            requestedCourse.setTeacher(proposedTeacher);
            proposedTeacher.setCourse(requestedCourse);
        }
    }

    public Course getRequestedCourse() {
        return requestedCourse;
    }

    public Teacher getProposedTeacher() {
        return proposedTeacher;
    }
}
