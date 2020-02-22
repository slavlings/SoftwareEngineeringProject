package model.exceptions;

public class NoTeachRequirementsSetException extends Exception {
    public NoTeachRequirementsSetException() {super("There are no teaching requirements set for the given course.");}
}
