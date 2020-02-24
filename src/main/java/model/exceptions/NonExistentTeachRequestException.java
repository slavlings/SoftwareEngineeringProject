package model.exceptions;

public class NonExistentTeachRequestException extends Exception {
    public NonExistentTeachRequestException() {
        super("There is no Teacher Request for this Course.");
    }
}
