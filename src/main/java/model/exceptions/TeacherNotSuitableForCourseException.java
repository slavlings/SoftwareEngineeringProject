package model.exceptions;

public class TeacherNotSuitableForCourseException extends Exception {
    public TeacherNotSuitableForCourseException() {
        super("The Teacher does not fulfill the Course Requirements.");
    }
}
