package model.exceptions;

public class NonExistentTeachRequestException extends Exception {
    public NonExistentTeachRequestException() {
        super("There is no Teaching Request for this Course.");
    }
}
