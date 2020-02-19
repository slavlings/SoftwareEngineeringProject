package model.exceptions;

public class TeachRequestAlreadyGivenException extends Exception {
    public TeachRequestAlreadyGivenException() {
        super("There is already a Teach Request for this model.Course.");
    }
}
