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
            TeachRequestMap teachRequestMap = new TeachRequestMap();

            Course course = mock(Course.class);
            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            assertThrows(NonExistentTeachRequestException.class, () -> teachRequestMap.approveTeachRequest(course));
        }

        @DisplayName("If there is no proposed teacher, the request isn't approved and it isn't removed from the map.")
        @Test
        public void noProposedTeacher() throws NoProposedTeacherException, TeacherNotCompletedTrainingException, NonExistentTeachRequestException {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            TeachRequest teachRequest = mock(TeachRequest.class);

            //teachRequest.approve() throws a NoProposedTeacherException
            doThrow(new NoProposedTeacherException()).when(teachRequest).approve();

            //add teachRequest to the underlying map under CourseName
            teachRequestMap.teachRequestMap.put("CourseName", teachRequest);

            Course course = mock(Course.class);
            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            teachRequestMap.approveTeachRequest(course);

            //teachRequest is still in the underlying map
            assertEquals(teachRequest, teachRequestMap.teachRequestMap.get("CourseName"));
        }

        @DisplayName("If the proposed teacher hasn't completed his training, " +
                "the request isn't approved and it isn't removed from the map.")
        @Test
        public void notCompletedTraining() throws NoProposedTeacherException, TeacherNotCompletedTrainingException, NonExistentTeachRequestException {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            TeachRequest teachRequest = mock(TeachRequest.class);

            //teachRequest.approve() throws a TeacherNotCompletedTrainingException
            doThrow(new TeacherNotCompletedTrainingException()).when(teachRequest).approve();

            //add teachRequest to the underlying map under CourseName
            teachRequestMap.teachRequestMap.put("CourseName", teachRequest);

            Course course = mock(Course.class);
            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            teachRequestMap.approveTeachRequest(course);

            //teachRequest is still in the underlying map
            assertEquals(teachRequest, teachRequestMap.teachRequestMap.get("CourseName"));
        }

        @DisplayName("If the proposed teacher has completed his training" +
                     "(teachRequest.approve() throws no exceptions), " +
                "the request is approved and it is removed from the map.")
        @Test
        public void completedTraining() throws NonExistentTeachRequestException {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            TeachRequest teachRequest = mock(TeachRequest.class);

            //add teachRequest to the underlying map under CourseName
            teachRequestMap.teachRequestMap.put("CourseName", teachRequest);

            Course course = mock(Course.class);
            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            teachRequestMap.approveTeachRequest(course);

            //teachRequest is no longer in the underlying map
            assertFalse(teachRequestMap.teachRequestMap.containsKey("CourseName"));
        }

    }

    @Nested
    @DisplayName("Teacher proposal tests")
    public class TeacherProposal {

        @DisplayName("The TeachRequest must have been placed into the teachRequestMap.")
        @Test
        public void throwsIfNoSuchTeachRequest() {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);

            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            assertThrows(NonExistentTeachRequestException.class, () -> teachRequestMap.proposeTeacher(course, teacher));
        }

        @DisplayName("The teacher must satisfy the teaching requirements.")
        @Test
        public void notSatisfyingTeachReqs() {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);
            TeachRequest teachRequest = mock(TeachRequest.class);

            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            //teacher does not satisfy the teaching requirements
            when(teacher.satisfiesTeachReqs(course)).thenReturn(false);

            //add teachRequest to the underlying map under CourseName
            teachRequestMap.teachRequestMap.put("CourseName", teachRequest);

            assertThrows(TeacherNotSuitableForCourseException.class, () -> teachRequestMap.proposeTeacher(course, teacher));
        }

        @DisplayName("If there is a teach request and the requirements are met," +
                "nothing should be thrown.")
        @Test
        public void proposalOK() {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);
            TeachRequest teachRequest = mock(TeachRequest.class);

            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            //teacher satisfies the teaching requirements
            when(teacher.satisfiesTeachReqs(course)).thenReturn(true);

            //add teachRequest to the underlying map under CourseName
            teachRequestMap.teachRequestMap.put("CourseName", teachRequest);

            assertDoesNotThrow(() -> teachRequestMap.proposeTeacher(course, teacher));
        }

    }

    @Nested
    @DisplayName("Teacher addition tests")
    public class TeacherAddition {

        @DisplayName("The TeachRequest mustn't have been placed into the teachRequestMap.")
        @Test
        public void throwsIfTeachRequestPresent() {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            Course course = mock(Course.class);

            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            //add new TeachRequest for course to the underlying map under CourseName
            teachRequestMap.teachRequestMap.put("CourseName", new TeachRequest(course));

            assertThrows(TeachRequestAlreadyGivenException.class, () -> teachRequestMap.addTeachRequest(course));
        }

        @DisplayName("If the TeachRequest is new, it should be added to the map.")
        @Test
        public void newTeachRequest() throws TeachRequestAlreadyGivenException {
            TeachRequestMap teachRequestMap = new TeachRequestMap();
            Course course = mock(Course.class);

            //course's name is CourseName
            when(course.toString()).thenReturn("CourseName");

            teachRequestMap.addTeachRequest(course);
            assertTrue(teachRequestMap.teachRequestMap.containsKey("CourseName"));

        }

    }
}
