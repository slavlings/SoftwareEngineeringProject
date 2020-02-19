package model;

import model.exceptions.NoProposedTeacherException;
import model.exceptions.TeacherNotCompletedTrainingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeachRequestTest {
    @Nested
    @DisplayName("Teacher approval tests")
    public class TeacherApproval {

        @DisplayName("There must be a proposed teacher.")
        @Test
        public void throwsIfNoProposedTeacher() {
            Course course = mock(Course.class);

            TeachRequest teachRequest = new TeachRequest(course);
            assertThrows(NoProposedTeacherException.class, teachRequest::approve);
        }

        @DisplayName("The proposed teacher must have completed his training.")
        @Test
        public void throwsIfNoCompletedTraining() {
            Teacher teacher = mock(Teacher.class);
            Course course = mock(Course.class);

            when(teacher.completedTraining(course)).thenReturn(false);

            TeachRequest teachRequest = new TeachRequest(course);
            teachRequest.proposeTeacher(teacher);

            assertThrows(TeacherNotCompletedTrainingException.class, teachRequest::approve);
        }

        @DisplayName("If there is a proposed Teacher and " +
                "he has completed his Training, " +
                "nothing should be thrown.")
        @Test
        public void approvalOK() {
            Teacher teacher = mock(Teacher.class);
            Course course = mock(Course.class);

            when(teacher.completedTraining(course)).thenReturn(true);

            TeachRequest teachRequest = new TeachRequest(course);
            teachRequest.proposeTeacher(teacher);

            assertDoesNotThrow(teachRequest::approve);
        }

    }
}
