package model;

import model.exceptions.NonExistentTeachRequestException;
import model.exceptions.TeacherNotSuitableForCourseException;

import java.util.LinkedList;
import java.util.List;

public class Admin extends Employee {
    private List<Teacher> teacherList;
    private List<Course> classes;
    private TeachRequestMap teachRequestMap;

    public Admin(String name, List<Course> classes, TeachRequestMap teachRequestMap) {
        super(name);
        this.classes = classes;
        this.teachRequestMap = teachRequestMap;
    }

    public LinkedList<Teacher> findSuitableStaff(Course course) {
        LinkedList<Teacher> suitableTeacherList = new LinkedList <Teacher>();
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacherToCheck = teacherList.get(i);
            if(teacherToCheck.satisfiesTeachReqs(course)) {
                suitableTeacherList.add(teacherToCheck);
            }
        }
        return suitableTeacherList;
    }

    public void proposeTeacher(Course course, Teacher teacher) {
        try {
            teachRequestMap.proposeTeacher(course, teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTraining(Teacher teacher, String training) {
        teacher.addTraining(training);
    }

    public void completeTraining(Teacher teacher, String training) {
        teacher.completeTraining(training);
    }

    public void addCourse(Course course) {
        classes.add(course);
    }

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);
    }
}
