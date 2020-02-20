package model.exceptions;

public class NonExistentTeachRequestException extends Exception {
    public NonExistentTeachRequestException() {
        super("There is no Teach Request for this Course.");
    }
}
