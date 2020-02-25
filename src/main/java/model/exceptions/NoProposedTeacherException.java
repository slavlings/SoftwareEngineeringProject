package model.exceptions;

public class NoProposedTeacherException extends Exception {
    public NoProposedTeacherException() {
        super("There is no Teacher proposed for this Teaching Request.");
    }
}
