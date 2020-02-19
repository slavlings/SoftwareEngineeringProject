package model.exceptions;

public class TeacherNotSuitableForCourseException extends Exception {
    public TeacherNotSuitableForCourseException() {
        super("The model.Teacher does not fulfill the model.Course Requirements.");
    }
}
