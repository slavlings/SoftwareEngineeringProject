package model;

import com.fasterxml.jackson.annotation.*;
import model.exceptions.NoTeachRequirementsSetException;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the course
 * Holds information about the name, teaching requirements, term the course is taught in and teacher
 * Holds information about required training name for comparisons
 */
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Course.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Course {
    private String name;
    private List<String> teachRequirements;
    private int termOrSemester;
    private Teacher teacher;
    private String requiredTrainingName;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Course(@JsonProperty("name") String name,@JsonProperty("teachRequirements") List<String> teachRequirements,@JsonProperty("termOrSemester") int termOrSemester,@JsonProperty("teacher") Teacher teacher,@JsonProperty("requiredTrainingName") String requiredTrainingName) {
        this.name = name;
        this.teachRequirements = teachRequirements;
        this.termOrSemester = termOrSemester;
        this.teacher = teacher;
        this.requiredTrainingName = requiredTrainingName;
    }

    /**
     * Constructor.
     * points to null teacher and teachRequirements when initialized
     * @param name of the course
     * @param termOrSemester to indicate in which semester is the course taught
     * @param requiredTrainingName indicates training required for teaching the course
     * */
    public Course(String name, int termOrSemester, String requiredTrainingName) {
        this.name = name;
        this.requiredTrainingName = requiredTrainingName;
        this.termOrSemester = termOrSemester;
        this.teacher = null;
        this.teachRequirements = null;
    }

    /**
     * toString
     *  @return name of course
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
     * Used to determine if a teacher has the required skills to teach a course
     * @param skills skills a teacher possesses
     * @return boolean, true if teaching requirements match the skills the teacher has
     * @throws NoTeachRequirementsSetException the teaching requirements for the course haven't been set
     */
    public boolean requirementsFulfilled(List <String> skills) throws NoTeachRequirementsSetException {

        if(teachRequirements == null || teachRequirements.isEmpty()) {
            throw new NoTeachRequirementsSetException();
        } else {
            if ((skills.containsAll(teachRequirements))){
                return true;
            } else {
                return false;
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getTeachRequirements() {
        return teachRequirements;
    }

    public int getTermOrSemester() {
        return termOrSemester;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getRequiredTrainingName() {
        return requiredTrainingName;
    }
}
