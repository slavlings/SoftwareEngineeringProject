package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DataWrapper {

    private List<Teacher> teachers;
    private List<Course> courses;
    private List<ClassDirector> classDirectors;
    private Admin admin;
    private PTTDirector pttDirector;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DataWrapper(@JsonProperty("teachers") List<Teacher> teachers,@JsonProperty("courses") List<Course> courses,@JsonProperty("classDirectors") List<ClassDirector> classDirectors,@JsonProperty("admin") Admin admin,@JsonProperty("pttDirector") PTTDirector pttDirector) {
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
