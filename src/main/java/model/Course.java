package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the course
 * Holds information about the name, teaching requirements, term the course is taught in and teacher
 * Holds information about required training name for comparisons
 */

public class Course {
    private String name;
    private List<String> teachRequirements = null;
    private int termOrSemester;
    private Teacher teacher;
    private String requiredTrainingName;

    /**
     * Constructor.
     * points to null teacher when initialized
     * @param name of the course
     * @param termOrSemester to indicate in which semester is the course taught
     * @param requiredTrainingName indicates training required for teaching the course
     * */
    public Course(String name, int termOrSemester, String requiredTrainingName) {
        this.name = name;
        this.requiredTrainingName = requiredTrainingName;
        this.termOrSemester = termOrSemester;
        this.teacher = null;
    }

    /**
     * Constructor
     *  @return requiredTrainingName for the specific course
     */
    @Override
    public String toString() {
        return name;
    }

    /** Used to find the training required to teach the course
     *  @return requiredTrainingName for the specific course
     */
    public String getReqTraining() {
        return requiredTrainingName;
    }

    /**
     * @param teacher that teaches the course
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Used by class director who has access to this method to set teaching requirements for his directed course
     *  @param teachRequirements
     */
    public void setTeachRequirements(List<String> teachRequirements) {
        this.teachRequirements = teachRequirements;
    }


    /**
     * Used for comparison of required skills and actual skills in teacher
     *  @return boolean, true if teaching requirements match the skills the teacher has
     */
    public boolean requirementsFulfilled(List <String> skills) {
        if ((skills.containsAll(teachRequirements))){
            return true;
        }
        else
        {return false;}
    }


}
