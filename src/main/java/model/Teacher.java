package model;

import com.fasterxml.jackson.annotation.*;
import model.exceptions.NoTeachRequirementsSetException;
import model.exceptions.TeacherTrainingAlreadyPresentException;

import java.util.LinkedList;
import java.util.List;
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Teacher extends Employee{

    private List<String> skills;
    private List<String> completedTrainings;
    private List<String> uncompletedTrainings;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Teacher(@JsonProperty("name") String name,@JsonProperty("skills") List<String> skills,@JsonProperty("completedTrainings") List<String> completedTrainings,@JsonProperty("uncompletedTrainings") List<String> uncompletedTrainings) {
        super(name);
        this.skills = skills;
        this.completedTrainings = completedTrainings;
        this.uncompletedTrainings = uncompletedTrainings;
    }

    public Teacher(String name) {
        super(name);
        uncompletedTrainings = new LinkedList<>();
        completedTrainings = new LinkedList<>();
        skills = new LinkedList<>();
    }

    /**
     * Getter for the list of skills.
     * @return
     */
    public List<String> getSkills() {
        return skills;
    }

    /**
     * Adds a training to the list of uncompleted trainings.
     * @param training
     * @throws TeacherTrainingAlreadyPresentException
     */
    public void addTraining(String training) throws TeacherTrainingAlreadyPresentException {
        if(uncompletedTrainings.contains(training) || completedTrainings.contains(training)) {
            throw new TeacherTrainingAlreadyPresentException();
        } else {
            uncompletedTrainings.add(training);
        }

    }

    /**
     * Removes training from uncompletedTrainings;
     * Adds it to completedTrainings.
     * Does nothing if the string does not exist in the list.
     * Todo: Consider renaming to onTrainingCompleted.
     *
     * @param training
     */
    public void completeTraining(final String training){
        final boolean isRemoved = uncompletedTrainings.remove(training);
        if(isRemoved) {
            completedTrainings.add(training);
        }
    }

    /**
     * Checks if a teacher has completed the training for a given course.
     * @param course
     * @return true if completed; else false.
     */
    public boolean completedTraining(final Course course ){
        String reqTraining = course.getReqTraining();
        if(completedTrainings.contains(reqTraining)){
            return true;
        } else{
            return false;
        }
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public boolean satisfiesTeachReqs(Course course) throws NoTeachRequirementsSetException {
        return course.requirementsFulfilled(skills);
    }

    public List<String> getCompletedTrainings() {
        return completedTrainings;
    }

    public List<String> getUncompletedTrainings() {
        return uncompletedTrainings;
    }

}
