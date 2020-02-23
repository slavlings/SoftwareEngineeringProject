package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import model.exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents an Admin.
 * Has functionality for finding suitable staff, proposing teacher and adding/completing trainings for teachers
 */
public class Admin extends Employee {

    private TeachRequestMap teachRequestMap;

    /**
     * Constructor.
     *
     * @param name            name of admin
     * @param teachRequestMap holds teaching requests to be approved by the director
     */

    public Admin(String name, TeachRequestMap teachRequestMap) {
        super(name);
        this.teachRequestMap = teachRequestMap;

    }


    /**
     * Finds a list of teachers that are suitable for a specific course by satisfying the skill requirements for the course
     * @param course given course
     * @param teacherList list of all teachers
     * @return list of suitable teachers
     */
    /**
     * Attempts to find a list of teachers that are suitable for a specific course by satisfying the skill requirements for the course.
     * Throws an exception if the course doesn't have teaching requirements set.
     * @param course
     * @param teacherList
     * @return LinkedList of suitable teachers
     * @throws NoTeachRequirementsSetException the course doesn't have teaching requirements set
     */
    public LinkedList<Teacher> findSuitableStaff(Course course, List<Teacher> teacherList) throws NoTeachRequirementsSetException {
        LinkedList<Teacher> suitableTeacherList = new LinkedList<Teacher>();
        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacherToCheck = teacherList.get(i);
            if (teacherToCheck.satisfiesTeachReqs(course)) {
                suitableTeacherList.add(teacherToCheck);
            }
        }
        return suitableTeacherList;
    }

    /**
     * Attempts to propose a teacher for a given course.
     * Throws exceptions if unsuccessful.
     * @param course given course
     * @param teacher given teacher
     * @throws TeacherNotSuitableForCourseException the teacher is not suitable for the course
     * @throws NonExistentTeachRequestException there is no teaching request for the given course
     * @throws NoTeachRequirementsSetException there are no teaching requirements set for the course
     */
    public void proposeTeacher(Course course, Teacher teacher) throws TeacherNotSuitableForCourseException, NonExistentTeachRequestException, NoTeachRequirementsSetException {

        teachRequestMap.proposeTeacher(course, teacher);

    }

    /**
     * Adds the training using the teacher class method
     *
     * @param training given name of training
     * @param teacher  given teacher
     */

    public void addTraining(Teacher teacher, String training) {
        teacher.addTraining(training);
    }


    /**
     * Completes the training using the teacher class method
     *
     * @param training given name of training
     * @param teacher  given teacher
     */
    public void completeTraining(Teacher teacher, String training) {
        teacher.completeTraining(training);
    }

}
