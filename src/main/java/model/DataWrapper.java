package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class DataWrapper {

    private List<Teacher> teachers;
    private List<Course> courses;
    private List<CourseDirector> courseDirectors;
    private Admin admin;
    private PTTDirector pttDirector;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public DataWrapper(@JsonProperty("teachers") List<Teacher> teachers, @JsonProperty("courses") List<Course> courses, @JsonProperty("classDirectors") List<CourseDirector> courseDirectors, @JsonProperty("admin") Admin admin, @JsonProperty("pttDirector") PTTDirector pttDirector) {
        this.teachers = teachers;
        this.courses = courses;
        this.courseDirectors = courseDirectors;
        this.admin = admin;
        this.pttDirector = pttDirector;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<CourseDirector> getCourseDirectors() {
        return courseDirectors;
    }

    public Admin getAdmin() {
        return admin;
    }

    public PTTDirector getPttDirector() {
        return pttDirector;
    }

}
