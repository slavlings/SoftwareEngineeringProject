package model;

import model.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeachRequestMapTest {
    @Nested
    @DisplayName("Teacher approval tests")
    public class TeacherApproval {

        @DisplayName("The TeachRequest must have been placed into the teachRequestMap.")
        @Test
        public void throwsIfNoSuchTeachRequest() {
            TeachRequestMap map = new TeachRequestMap();
            Course course = mock(Course.class);
            when(course.toString()).thenReturn("CourseName");
            assertThrows(NonExistentTeachRequestException.class, () -> map.approveTeachRequest(course));
        }

        @DisplayName("If there is no proposed teacher, the request isn't approved and it isn't removed from the map.")
        @Test
        public void noProposedTeacher() throws NoProposedTeacherException, TeacherNotCompletedTrainingException, NonExistentTeachRequestException {
            TeachRequestMap map = new TeachRequestMap();
            TeachRequest tr = mock(TeachRequest.class);

            doThrow(new NoProposedTeacherException()).when(tr).approve();

            map.teachRequestMap.put("CourseName", tr);

            Course course = mock(Course.class);
            when(course.toString()).thenReturn("CourseName");

            map.approveTeachRequest(course);

            assertEquals(tr, map.teachRequestMap.get("CourseName"));
        }

        @DisplayName("If the proposed teacher hasn't completed his training, " +
                "the request isn't approved and it isn't removed from the map.")
        @Test
        public void notCompletedTraining() throws NoProposedTeacherException, TeacherNotCompletedTrainingException, NonExistentTeachRequestException {
            TeachRequestMap map = new TeachRequestMap();
            TeachRequest tr = mock(TeachRequest.class);

            doThrow(new TeacherNotCompletedTrainingException()).when(tr).approve();

            map.teachRequestMap.put("CourseName", tr);

            Course course = mock(Course.class);
            when(course.toString()).thenReturn("CourseName");

            map.approveTeachRequest(course);

            assertEquals(tr, map.teachRequestMap.get("CourseName"));
        }

        @DisplayName("If the proposed teacher has completed his training, " +
                "the request is approved and it is removed from the map.")
        @Test
        public void completedTraining() throws NonExistentTeachRequestException {
            TeachRequestMap map = new TeachRequestMap();
            TeachRequest tr = mock(TeachRequest.class);

            map.teachRequestMap.put("CourseName", tr);

            Course course = mock(Course.class);
            when(course.toString()).thenReturn("CourseName");

            map.approveTeachRequest(course);

            assertFalse(map.teachRequestMap.containsKey("CourseName"));
        }

    }

    @Nested
    @DisplayName("Teacher proposal tests")
    public class TeacherProposal {

        @DisplayName("The TeachRequest must have been placed into the teachRequestMap.")
        @Test
        public void throwsIfNoSuchTeachRequest() {
            TeachRequestMap map = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);
            when(course.toString()).thenReturn("CourseName");
            assertThrows(NonExistentTeachRequestException.class, () -> map.proposeTeacher(course, teacher));
        }

        @DisplayName("The teacher must satisfy the teaching requirements.")
        @Test
        public void notSatisfyingTeachReqs() {
            TeachRequestMap map = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);
            TeachRequest tr = mock(TeachRequest.class);
            when(course.toString()).thenReturn("CourseName");
            when(teacher.satisfiesTeachReqs(course)).thenReturn(false);

            map.teachRequestMap.put("CourseName", tr);

            assertThrows(TeacherNotSuitableForCourseException.class, () -> map.proposeTeacher(course, teacher));
        }

        @DisplayName("If there is a teach request and the requirements are met," +
                "nothing should be thrown.")
        @Test
        public void proposalOK() {
            TeachRequestMap map = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);
            TeachRequest tr = mock(TeachRequest.class);
            when(course.toString()).thenReturn("CourseName");
            when(teacher.satisfiesTeachReqs(course)).thenReturn(true);

            map.teachRequestMap.put("CourseName", tr);

            assertDoesNotThrow(() -> map.proposeTeacher(course, teacher));
        }

    }

    @Nested
    @DisplayName("Teacher addition tests")
    public class TeacherAddition {

        @DisplayName("The TeachRequest mustn't have been placed into the teachRequestMap.")
        @Test
        public void throwsIfTeachRequestPresent() {
            TeachRequestMap map = new TeachRequestMap();
            Course course = mock(Course.class);

            when(course.toString()).thenReturn("CourseName");
            map.teachRequestMap.put("CourseName", new TeachRequest(course));
            assertThrows(TeachRequestAlreadyGivenException.class, () -> map.addTeachRequest(course));
        }

        @DisplayName("If the TeachRequest is new, it should be added to the map.")
        @Test
        public void newTeachRequest() throws TeachRequestAlreadyGivenException {
            TeachRequestMap map = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);
            TeachRequest tr = mock(TeachRequest.class);
            when(course.toString()).thenReturn("CourseName");
            when(teacher.satisfiesTeachReqs(course)).thenReturn(false);

            map.addTeachRequest(course);
            assertTrue(map.teachRequestMap.containsKey("CourseName"));

        }

    }
}
