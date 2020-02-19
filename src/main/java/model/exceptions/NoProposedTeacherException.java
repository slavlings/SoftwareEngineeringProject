package model.exceptions;

public class NoProposedTeacherException extends Exception {
    public NoProposedTeacherException() {
        super("There is no model.Teacher proposed for this Teach Request.");
    }
}
