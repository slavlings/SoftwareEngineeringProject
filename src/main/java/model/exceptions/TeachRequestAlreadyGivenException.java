package model.exceptions;

public class TeachRequestAlreadyGivenException extends Exception {
    public TeachRequestAlreadyGivenException() {
        super("There is already a Teacher Request for this Course.");
    }
}
