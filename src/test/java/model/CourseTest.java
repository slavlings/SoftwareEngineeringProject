package model;

import model.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CourseTest {
    @Nested
    @DisplayName("Requirements fulfilled tests")
    public class RequirementsFulfilled {

        @DisplayName("Teaching requirements are null")
        @Test
        public void nullTeachReqs() {
            Course course = new Course("Maths",null,1,mock(Teacher.class),"Required Training");

            LinkedList<String> skills = new LinkedList<>();
            skills.add("skill");

            assertThrows(NoTeachRequirementsSetException.class, () -> course.requirementsFulfilled(skills));
        }

        @DisplayName("Teaching requirements are empty.")
        @Test
        public void emptyTeachReqs() {
            Course course = new Course("Maths",new LinkedList<>(),1,mock(Teacher.class),"Required Training");

            LinkedList<String> skills = new LinkedList<>();
            skills.add("skill");

            assertThrows(NoTeachRequirementsSetException.class, () -> course.requirementsFulfilled(skills));

        }

        @DisplayName("Skills contain none of the teaching requirements.")
        @Test
        public void noTeachReqsContained() throws NoTeachRequirementsSetException {

            LinkedList<String> teachReqs = new LinkedList<>();
            teachReqs.add("req1");
            teachReqs.add("req2");

            Course course = new Course("Maths",teachReqs,1,mock(Teacher.class),"Required Training");

            LinkedList<String> skills = new LinkedList<>();
            skills.add("skill");

            assertEquals(false, course.requirementsFulfilled(skills));
        }

        @DisplayName("Skills contain some, but not all of the teaching requirements.")
        @Test
        public void notAllTeachReqsContained() throws NoTeachRequirementsSetException {
            LinkedList<String> teachReqs = new LinkedList<>();
            teachReqs.add("req1");
            teachReqs.add("req2");

            Course course = new Course("Maths",teachReqs,1,mock(Teacher.class),"Required Training");

            LinkedList<String> skills = new LinkedList<>();
            skills.add("req1");

            assertEquals(false, course.requirementsFulfilled(skills));
        }

        @DisplayName("Skills contain all of the teaching requirements.")
        @Test
        public void allTeachReqsContained() throws NoTeachRequirementsSetException {
            LinkedList<String> teachReqs = new LinkedList<>();
            teachReqs.add("req1");
            teachReqs.add("req2");

            Course course = new Course("Maths",teachReqs,1,mock(Teacher.class),"Required Training");

            LinkedList<String> skills = new LinkedList<>();
            skills.add("req1");
            skills.add("req2");
            skills.add("skill");

            assertEquals(true, course.requirementsFulfilled(skills));
        }

    }

}
