package model;

import com.fasterxml.jackson.annotation.*;
import model.exceptions.*;

import java.util.HashMap;

/**
 * Represents a HashMap that holds teaching requests.
 * Adds functionality for adding and approving teaching requests,
 * and proposing teachers.
 */
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = TeachRequestMap.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TeachRequestMap {

    HashMap<String, TeachRequest> teachRequestMap;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TeachRequestMap(@JsonProperty("teachRequestMap") HashMap<String, TeachRequest> teachRequestMap) {
        this.teachRequestMap = teachRequestMap;
    }

    /**
     * Constructor.
     */
    public TeachRequestMap() {
        teachRequestMap = new HashMap<>();
    }

    /**
     * Attempts to add a teaching request for the given course to this TeachRequestMap.
     * Throws an exception if unsuccessful.
     * @param course given course
     * @throws TeachRequestAlreadyGivenException a request for the given course has already been added
     */
    public void addTeachRequest(Course course) throws TeachRequestAlreadyGivenException {
        if (teachRequestMap.containsKey(course.toString())) {
            //a request for the given course has already been added
            throw new TeachRequestAlreadyGivenException();
        } else {
            //add a new teaching request for the given course
            teachRequestMap.put(course.toString(), new TeachRequest(course));
        }
    }


    /**
     * Attempt to propose the given teacher for a teaching request associated with the given course.
     * Throws an exception if unsuccessful.
     * @param course given course
     * @param teacher given teacher
     * @throws NonExistentTeachRequestException the teaching request does not exist
     * @throws TeacherNotSuitableForCourseException the teacher is not suitable for the course
     * @throws NoTeachRequirementsSetException the course doesn't have teaching requirements set
     */
    public void proposeTeacher(Course course, Teacher teacher) throws NonExistentTeachRequestException, TeacherNotSuitableForCourseException, NoTeachRequirementsSetException {
        String courseName = course.toString();

        if (!teachRequestMap.containsKey(courseName)) {
            //there is no teaching request for the given course
            throw new NonExistentTeachRequestException();
        } else if (!teacher.satisfiesTeachReqs(course)) {
            //the given teacher is not suitable for the given course
            throw new TeacherNotSuitableForCourseException();
        } else {
            //propose the teacher
            TeachRequest request = teachRequestMap.get(courseName);
            request.proposeTeacher(teacher);
        }
    }

    /**
     * Attempts to approve a teaching request for the given course.
     * If unsuccessful, throws an exception.
     * @param course given course
     * @throws NonExistentTeachRequestException the teaching request does not exist
     * @throws NoProposedTeacherException there is no proposed teacher
     * @throws TeacherNotCompletedTrainingException the proposed teacher hasn't completed the required training
     */
    public void approveTeachRequest(Course course) throws NonExistentTeachRequestException, NoProposedTeacherException, TeacherNotCompletedTrainingException {

        String courseName = course.toString();

        if (!teachRequestMap.containsKey(courseName)) {
            //there is no teaching request for the given course
            throw new NonExistentTeachRequestException();
        } else {
            //try to approve the request and print any exception trace if unsuccessful
            TeachRequest request = teachRequestMap.get(courseName);
            try {
                request.approve();
                teachRequestMap.remove(courseName);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public HashMap<String, TeachRequest> getTeachRequestMap() {
        return teachRequestMap;
    }
}
