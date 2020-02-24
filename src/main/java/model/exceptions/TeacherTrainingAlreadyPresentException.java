package model.exceptions;

public class TeacherTrainingAlreadyPresentException extends Exception{
    public TeacherTrainingAlreadyPresentException() {
        super("The teacher already has the given training as assigned.");
    }
}
