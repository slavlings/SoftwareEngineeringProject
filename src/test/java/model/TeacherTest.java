package model;

import model.exceptions.TeacherTrainingAlreadyPresentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class TeacherTest {

    @Nested
    @DisplayName("Add training tests")
    public class AddTraining {

        @DisplayName("The training is already assigned, but uncompleted.")
        @Test
        public void assignedUncompleted() {
            LinkedList<String> uncompletedTrainings = new LinkedList<>();
            LinkedList<String> completedTrainings = new LinkedList<>();
            String training = "test training";
            uncompletedTrainings.add(training);
            Teacher teacher = new Teacher("John",new LinkedList<>(),completedTrainings,uncompletedTrainings);
            assertThrows(TeacherTrainingAlreadyPresentException.class, () -> teacher.addTraining(training));
        }

        @DisplayName("The training is already assigned and completed.")
        @Test
        public void assignedCompleted() {
            LinkedList<String> uncompletedTrainings = new LinkedList<>();
            LinkedList<String> completedTrainings = new LinkedList<>();
            String training = "test training";
            completedTrainings.add(training);
            Teacher teacher = new Teacher("John",new LinkedList<>(),completedTrainings,uncompletedTrainings);
            assertThrows(TeacherTrainingAlreadyPresentException.class, () -> teacher.addTraining(training));


        }

        @DisplayName("The training is not assigned.")
        @Test
        public void notAssigned() {
            LinkedList<String> uncompletedTrainings = new LinkedList<>();
            LinkedList<String> completedTrainings = new LinkedList<>();
            String training = "test training";

            Teacher teacher = new Teacher("John",new LinkedList<>(),completedTrainings,uncompletedTrainings);
            assertDoesNotThrow(() -> teacher.addTraining(training));
        }

    }

    @Nested
    @DisplayName("Complete training tests")
    public class CompleteTraining {

        @DisplayName("The training is assigned and is uncompleted")
        @Test
        public void assignedUncompleted() {
            LinkedList<String> uncompletedTrainings = new LinkedList<>();
            LinkedList<String> completedTrainings = new LinkedList<>();
            String training = "test training";
            uncompletedTrainings.add(training);
            Teacher teacher = new Teacher("John",new LinkedList<>(),completedTrainings,uncompletedTrainings);

            teacher.completeTraining(training);

            assertEquals(true,completedTrainings.contains(training));
            assertEquals(false,uncompletedTrainings.contains(training));
        }

        @DisplayName("The training is unassigned.")
        @Test
        public void unassigned() {
            LinkedList<String> uncompletedTrainings = new LinkedList<>();
            LinkedList<String> completedTrainings = new LinkedList<>();
            String training = "test training";
            Teacher teacher = new Teacher("John",new LinkedList<>(),completedTrainings,uncompletedTrainings);

            teacher.completeTraining(training);

            assertEquals(false,completedTrainings.contains(training));

        }

    }
}
