package model;

import java.util.List;

public class DataWrapper {

    private List<Teacher> teachers;
    private List<Course> courses;
    private List<ClassDirector> classDirectors;
    private Admin admin;
    private PTTDirector pttDirector;

    public DataWrapper(List<Teacher> teachers, List<Course> courses, List<ClassDirector> classDirectors, Admin admin, PTTDirector pttDirector) {
        this.teachers = teachers;
        this.courses = courses;
        this.classDirectors = classDirectors;
        this.admin = admin;
        this.pttDirector = pttDirector;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<ClassDirector> getClassDirectors() {
        return classDirectors;
    }

    public Admin getAdmin() {
        return admin;
    }

    public PTTDirector getPttDirector() {
        return pttDirector;
    }
}
