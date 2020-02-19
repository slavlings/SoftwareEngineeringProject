package model.exceptions;

public class TeacherNotCompletedTrainingException extends Exception {
    public TeacherNotCompletedTrainingException() {
        super("The model.Teacher has not completed the required Training.");
    }
}
