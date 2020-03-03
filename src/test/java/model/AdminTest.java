package model;

import model.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

public class AdminTest {

    @Nested
    @DisplayName("Find Suitable staff tests")
    public class FindSuitableStaff {

        @DisplayName("The course has no teaching requirements set")
        @Test
        public void noTeachRequirementsSet() throws NoTeachRequirementsSetException {
            Teacher teacher = mock(Teacher.class);
            Course course = mock(Course.class);
            doThrow(new NoTeachRequirementsSetException()).when(teacher).satisfiesTeachReqs(course);

            LinkedList<Teacher> teachers = new LinkedList<>();
            teachers.add(teacher);

            Admin admin = new Admin("John", new TeachRequestMap());

            assertThrows(NoTeachRequirementsSetException.class, () -> admin.findSuitableStaff(course,teachers));
        }

        @DisplayName("No suitable staff to find.")
        @Test
        public void noSuitableStaff() throws NoTeachRequirementsSetException {
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);

            //course's name is CourseName
            when(teacher.satisfiesTeachReqs(course)).thenReturn(false);

            LinkedList<Teacher> teachers = new LinkedList<>();
            teachers.add(teacher);

            Admin admin = new Admin("John", new TeachRequestMap());

            assertEquals(true,admin.findSuitableStaff(course,teachers).isEmpty());

        }

        @DisplayName("There is suitable staff to find.")
        @Test
        public void suitableStaffExists() throws NoTeachRequirementsSetException {
            Course course = mock(Course.class);
            Teacher teacher = mock(Teacher.class);

            //course's name is CourseName
            when(teacher.satisfiesTeachReqs(course)).thenReturn(true);

            LinkedList<Teacher> teachers = new LinkedList<>();
            teachers.add(teacher);

            Admin admin = new Admin("John", new TeachRequestMap());

            assertEquals(false,admin.findSuitableStaff(course,teachers).isEmpty());
        }

    }
}
