package model.exceptions;

public class TeachRequestAlreadyGivenException extends Exception {
    public TeachRequestAlreadyGivenException() {
        super("There is already a Teaching Request for this Course.");
    }
}
