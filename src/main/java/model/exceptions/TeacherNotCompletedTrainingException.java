package model.exceptions;

public class TeacherNotCompletedTrainingException extends Exception {
    public TeacherNotCompletedTrainingException() {
        super("The Teacher has not completed the required Training.");
    }
}
