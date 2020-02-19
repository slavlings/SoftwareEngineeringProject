package model;

import model.exceptions.NoProposedTeacherException;
import model.exceptions.TeacherNotCompletedTrainingException;

public class TeachRequest {
    private Course requestedCourse;
    private Teacher proposedTeacher;

    public TeachRequest(Course requestedCourse) {
        this.requestedCourse = requestedCourse;
        this.proposedTeacher = null;
    }

    public void proposeTeacher(Teacher teacher) {
        proposedTeacher = teacher;
    }

    public void approve() throws NoProposedTeacherException, TeacherNotCompletedTrainingException {
        if (proposedTeacher == null) {
            throw new NoProposedTeacherException();
        } else if (!proposedTeacher.completedTraining(requestedCourse)) {
            throw new TeacherNotCompletedTrainingException();
        } else {
            requestedCourse.setTeacher(proposedTeacher);
            proposedTeacher.setCourse(requestedCourse);
        }
    }
}
