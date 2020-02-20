package model;

import model.exceptions.NoProposedTeacherException;
import model.exceptions.NonExistentTeachRequestException;
import model.exceptions.TeacherNotCompletedTrainingException;
import model.exceptions.TeacherNotSuitableForCourseException;

import java.util.LinkedList;
import java.util.List;


/**
 * Represents an Admin.
 * Has functionality for finding suitable staff, proposing teacher, adding training/course/teacher and completing training
 */

public class Admin extends Employee {
    private List<Teacher> teacherList;
    private List<Course> classes;
    private TeachRequestMap teachRequestMap;

    /**
     * Constructor.
     *
     * @param name            name of admin
     * @param teacherList     holds a list of teachers
     * @param classes         holds a list of courses
     * @param teachRequestMap holds teaching requests to be approved by the director
     */

    public Admin(String name, List<Course> classes, TeachRequestMap teachRequestMap) {
        super(name);
        this.classes = classes;
        this.teachRequestMap = teachRequestMap;
    }

    /**
     * Finds a list of teachers that are suitable for a specific course by satisfying the skill requirements for the course
     *
     * @param course given course
     * @return list of applicable teachers
     */
    public LinkedList<Teacher> findSuitableStaff(Course course) {
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
     * Proposes a teacher for a given course
     *
     * @param course  given course
     * @param teacher given teacher
     */
    public void proposeTeacher(Course course, Teacher teacher) {

        /**
         * Try to propose the teacher, throws exceptions if unsuccessful as per the TeachRequest class
         * @throws NoProposedTeacherException when no proposed teacher
         * @throws TeacherNotCompletedTrainingException when proposed teacher hasn't completed the required training
         */
        try {
            teachRequestMap.proposeTeacher(course, teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Completes the training using the teacher class method
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

    /**
     * Adds a course to the list of courses
     *
     * @param course given course
     */

    public void addCourse(Course course) {
        classes.add(course);
    }

    /**
     * Adds a teacher to the list of teachers
     *
     * @param teacher given teacher
     */

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);
    }
}
