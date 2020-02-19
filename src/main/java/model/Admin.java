package model;

import model.exceptions.NonExistentTeachRequestException;
import model.exceptions.TeacherNotSuitableForCourseException;

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

    public List<Teacher> findSuitableStaff(Course course) {
        List<Teacher> suitableTeacherList = null;
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).requirementsFulfilled(teacherList.get(i).getSkills())) {
                suitableTeacherList.add(teacherList.get(i));
            }
        }
        return suitableTeacherList;
    }

    public void proposeTeacher(Course course, Teacher teacher) throws TeacherNotSuitableForCourseException, NonExistentTeachRequestException {
        teachRequestMap.proposeTeacher(course, teacher);
    }

    public void addTraining(Teacher teacher, String training) {
        teacher.addTraining(training);
    }

    public void addCourse(Course course) {
        classes.add(course);
    }

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);
    }
}
