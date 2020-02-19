package model;

import model.exceptions.NonExistentTeachRequestException;
import model.exceptions.TeachRequestAlreadyGivenException;
import model.exceptions.TeacherNotSuitableForCourseException;

import java.util.HashMap;

public class TeachRequestMap {

    HashMap<String, TeachRequest> teachRequestMap;

    public TeachRequestMap() {
        teachRequestMap = new HashMap<>();
    }

    public void addTeachRequest(Course course) throws TeachRequestAlreadyGivenException {
        if (teachRequestMap.containsKey(course.toString())) {
            throw new TeachRequestAlreadyGivenException();
        } else {
            teachRequestMap.put(course.toString(), new TeachRequest(course));
        }
    }

    public void proposeTeacher(Course course, Teacher teacher) throws NonExistentTeachRequestException, TeacherNotSuitableForCourseException {
        if (!teachRequestMap.containsKey(course.toString())) {
            throw new NonExistentTeachRequestException();
        } else if (!teacher.satisfiesTeachReqs(course)) {
            throw new TeacherNotSuitableForCourseException();
        } else {
            TeachRequest request = teachRequestMap.get(course.toString());
            request.proposeTeacher(teacher);
        }
    }

    public void approveTeachRequest(Course course) throws NonExistentTeachRequestException {
        if (!teachRequestMap.containsKey(course.toString())) {
            throw new NonExistentTeachRequestException();
        } else {
            TeachRequest request = teachRequestMap.get(course.toString());
            try {
                request.approve();
                teachRequestMap.remove(course.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
