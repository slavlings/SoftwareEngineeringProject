import model.*;
import model.exceptions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class. Handles UI and the method calls to the classes in the model package.
 */
public class Controller {

    private List<Teacher> teachers;
    private List<Course> courses;
    private List<CourseDirector> courseDirectors;
    private PTTDirector pttDirector;
    private Admin admin;
    private Scanner scanner;
    private CourseDirector selectedCourseDirector = null;
    private InputOutput io = InputOutput.getInstance();

    public Controller() {
        DataWrapper dataWrapper = io.readFromJSON();
        teachers = dataWrapper.getTeachers();
        courses = dataWrapper.getCourses();
        courseDirectors = dataWrapper.getCourseDirectors();
        pttDirector = dataWrapper.getPttDirector();
        admin = dataWrapper.getAdmin();
        scanner = new Scanner(System.in);
    }

    /**
     * Starts the controller.
     * Enters the main menu.
     */
    public void start() {
        while (true) {
            System.out.println("Pick user:");
            System.out.println("-1 -> QUIT");
            System.out.println("1 -> Admin");
            System.out.println("2 -> PTTDirector");
            System.out.println("3 -> CourseDirector");
            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                scanner.nextLine();
                switch (userInput) {
                    case -1:
                        quit();
                        break;

                    case 1:
                        adminSubMenu();
                        break;

                    case 2:
                        pttDirectorSubMenu();
                        break;

                    case 3:
                        courseDirectorSubMenu();
                        break;

                    default:
                        continue;
                }
            } else {
                scanner.nextLine();
            }

        }
    }

    /**
     * Enters the admin submenu.
     */
    private void adminSubMenu() {
        while (true) {
            System.out.println("Pick action:");
            System.out.println("-1 -> QUIT");
            System.out.println("0 -> Go Back");
            System.out.println("1 -> Find and propose qualified teachers for a course");
            System.out.println("2 -> Mark teacher training as completed");
            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput == 0) {
                    break;
                } else {
                    switch (userInput) {
                        case -1:
                            quit();
                            break;

                        case 1:
                            suitableTeachersSubMenu();
                            break;

                        case 2:
                            completeTrainingSubMenu();
                            break;

                        default:
                            continue;
                    }
                }
            } else {
                scanner.nextLine();
            }
        }
    }

    /**
     * Enters the suitable teachers submenu.
     */
    private void suitableTeachersSubMenu() {
        System.out.println("Which course do you want to find suitable teachers for?");
        System.out.println("Type in course name or 0 to go back.");
        String userInput = null;
        Course course = null;
        while (true) {
            userInput = scanner.nextLine();
            course = getCourse(userInput);
            if (course != null || userInput.equals("0")) {
                break;
            }
            System.out.println("No course match found. Try again.");
        }

        if (course != null) {
            LinkedList<Teacher> suitableTeachers = null;
            try {
                suitableTeachers = admin.findSuitableStaff(course, teachers);
            } catch (NoTeachRequirementsSetException e) {
                System.out.println(e.getMessage());
            }
            if (suitableTeachers == null || suitableTeachers.isEmpty()) {
                System.out.println("No suitable teachers were found.");
            } else {
                //indices start from 1 for user but from 0 for code
                int index = 1;
                System.out.println("Here are the suitable teachers.");
                for (Teacher teacher : suitableTeachers) {
                    System.out.println(index++ + ": " + teacher.toString());
                }
                System.out.println("Which teacher do you want to propose for the course?");
                //-1 to go back in code, even though 0 for user
                System.out.println("Type in teacher index or 0 to go back.");
                int intUserInput;
                while (true) {
                    if (scanner.hasNextInt()) {
                        intUserInput = scanner.nextInt();
                        scanner.nextLine();
                        intUserInput--;
                        if (intUserInput >= -1 && intUserInput < suitableTeachers.size()) {
                            break;
                        }
                    } else {
                        scanner.nextLine();
                    }
                    System.out.println("Incorrect input. Try again.");
                }
                if (intUserInput > -1) {
                    Teacher proposedTeacher = suitableTeachers.get(intUserInput);
                    try {
                        admin.proposeTeacher(course, proposedTeacher);
                        System.out.println("Proposal successful.");
                        System.out.println("Do you want to assign the required training for the course to the proposed teacher?");
                        System.out.println("Type in yes to proceed or no to go back");
                        while(true) {
                            userInput = scanner.nextLine();
                            if(userInput.equals("yes") || userInput.equals("no")) {
                                break;
                            }
                            System.out.println("Incorrect input. Try again.");
                        }
                        if(userInput.equals("yes")) {
                            admin.addTraining(proposedTeacher, course.getRequiredTrainingName());
                            System.out.println("Training successfully assigned.");
                        }
                    } catch (Exception e) {
                        System.out.println("Proposal unsuccessful.");
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Enters the PTTDirector submenu.
     */
    private void pttDirectorSubMenu() {
        while (true) {
            System.out.println("Pick action:");
            System.out.println("-1 -> QUIT");
            System.out.println("0 -> Go Back");
            System.out.println("1 -> Approve teaching request");
            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput == 0) {
                    break;
                } else {
                    switch (userInput) {
                        case -1:
                            quit();
                            break;

                        case 1:
                            approveTeachRequestSubMenu();
                            break;
                    }
                }
            } else {
                scanner.nextLine();
            }
        }
    }

    /**
     * Enters the submenu for completing a teacher's training.
     */
    private void completeTrainingSubMenu() {
        System.out.println("Which teacher do you want to complete training for?");
        System.out.println("Type in teacher's name or 0 to go back.");
        String userInput = null;
        Teacher selectedTeacher = null;

        while (true) {
            userInput = scanner.nextLine();
            selectedTeacher = getTeacher(userInput);
            if (selectedTeacher != null || userInput.equals("0")) {
                break;
            }
            System.out.println("No teacher match found. Try again.");
        }

        if (selectedTeacher != null) {
            List<String> uncompletedTrainings = selectedTeacher.getUncompletedTrainings();
            if ((uncompletedTrainings == null) || (uncompletedTrainings.isEmpty())) {
                System.out.println("There are no uncompleted trainings for the teacher.");
            } else {
                System.out.println("There are the following uncompleted trainings for this teacher.");
                System.out.println("Which one do you want to mark as completed? ");
                System.out.println("Select index or 0 to go back");
                int index = 1;
                for (String training : uncompletedTrainings) {
                    System.out.println(index++ + ": " + training);
                }
                int intUserInput;
                while (true) {
                    if (scanner.hasNextInt()) {
                        intUserInput = scanner.nextInt();
                        scanner.nextLine();
                        intUserInput--;
                        if (intUserInput >= -1 && intUserInput < uncompletedTrainings.size()) {
                            break;
                        }
                    } else {
                        scanner.nextLine();
                    }
                    System.out.println("Incorrect input. Try again.");
                }
                if (intUserInput > -1) {
                    String selectedTraining = uncompletedTrainings.get(intUserInput);
                    admin.completeTraining(selectedTeacher,selectedTraining);
                    System.out.println("Training has been completed.");
                }
            }
        }
    }

    /**
     * Enters the submenu for approving a teaching request.
     */
    private void approveTeachRequestSubMenu() {
            System.out.println("For which course do you want to approve a teaching request?");
            System.out.println("Type in course name or 0 to go back.");
            String userInput = null;
            Course course = null;
            while (true) {
                userInput = scanner.nextLine();
                course = getCourse(userInput);
                if (course != null || userInput.equals("0")) {
                    break;
                }
                System.out.println("No course match found. Try again.");
            }
            if (course != null) {
                try {
                    pttDirector.approveTeachRequest(course);
                    System.out.println("You have approved a teaching request for the course " + course);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

    }

    /**
     * Enters the course director submenu.
     */
    private void courseDirectorSubMenu() {
        while (true) {
            System.out.println("Pick action:");
            System.out.println("-1 -> QUIT");
            System.out.println("0 -> Go Back");
            System.out.println("1 -> Select Your Course");
            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput == 0) {
                    break;
                } else {
                    switch (userInput) {
                        case -1:
                            quit();
                            break;

                        case 1:
                            selectCourseDirectorSubMenu();
                            break;

                        default:
                            continue;
                    }
                }
            } else {
                scanner.nextLine();
            }
        }
    }

    /**
     * Enters the submenu for selecting a course director.
     */
    private void selectCourseDirectorSubMenu() {
        while (true) {
            System.out.println("Which course are you teaching?");
            System.out.println("Type in the name of the course, 0 to go back, or -1 to quit.");
            String userInput = scanner.nextLine();
            if(userInput.equals("0")) {
                break;
            }
            if(userInput.equals("-1")) {
                quit();
            }
            selectedCourseDirector = getCourseDirector(userInput);
            if (selectedCourseDirector != null) {
                break;
            }
            System.out.println("No course match found. Try again.");
        }
        if(selectedCourseDirector != null) {
            specificCourseDirectorSubMenu();
        }
    }

    /**
     * Enter the submenu for a specific course director.
     */
    private void specificCourseDirectorSubMenu() {
        while (true) {
            System.out.println("Pick action:");
            System.out.println("-1 -> QUIT");
            System.out.println("0 -> Go Back");
            System.out.println("1 -> Set course teaching requirements");
            System.out.println("2 -> Add teacher request");

            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                scanner.nextLine();
                if (userInput == 0) {
                    break;
                } else {
                    switch (userInput) {
                        case -1:
                            quit();
                            break;

                        case 1:
                            setCourseTeachingRequirements();
                            break;

                        case 2:
                            addTeacherRequest();
                            break;

                        default:
                            continue;
                    }
                }
            } else {
                scanner.nextLine();
            }
        }
    }

    /**
     * Enter the submenu for setting the teaching requirements of a course.
     */
    private void setCourseTeachingRequirements() {
        LinkedList<String> skillsToAdd = new LinkedList<String>();
        while (true) {
            System.out.println("Type in skills for teaching requirements - type a skill and press enter to insert. You can type in as many skills as you want. Finish by pressing 1. ");
            System.out.println("-1 -> QUIT");
            System.out.println("0 -> Go Back");
            System.out.println("1 -> Finish adding");

            String userInput = null;
            while (true) {
                userInput = scanner.nextLine();
                if (userInput.equals("-1")) {
                    quit();
                } else if (userInput.equals("0") || userInput.equals("1")) {
                    break;
                } else {
                    if(!skillsToAdd.contains(userInput)) {
                        skillsToAdd.add(userInput);
                    } else {
                        System.out.println("You already added this skill.");
                    }
                }
            }
            if(userInput.equals("1")) {
                selectedCourseDirector.setCourseTeachRequirements(skillsToAdd);

            }
            break;
        }
    }

    /**
     * Enter the submenu for adding a teaching request.
     */
    private void addTeacherRequest() {
        try {
            selectedCourseDirector.addTeachRequest();
            System.out.println("You have added a teach request for the course " + selectedCourseDirector.getDirectedCourse());
        } catch (TeachRequestAlreadyGivenException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the app data to the staff.json file and quits the controller.
     */
    private void quit() {
        DataWrapper dataWrapper = new DataWrapper(teachers, courses, courseDirectors, admin, pttDirector);
        io.writeToJSON(dataWrapper);
        System.exit(0);
    }

    /**
     * Returns a course with the given name or null if
     * no such course is found.
     * @param name given name
     * @return course
     */
    private Course getCourse(String name) {

        for (Course course : courses) {
            if (course.toString().equals(name)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Returns the course director for a given course name
     * or null if no such course director is found.
     * @param courseName given course name
     * @return course director
     */
    private CourseDirector getCourseDirector(String courseName) {

        for(CourseDirector courseDirector : courseDirectors) {
            if(courseName.equals(courseDirector.getDirectedCourse().toString())) {
                return courseDirector;
            }
        }
        return null;
    }

    /**
     * Returns the teacher with the given name
     * or null if no such teacher is found.
     * @param teacherName given teacher name
     * @return teacher
     */
    private Teacher getTeacher(String teacherName) {
        for(Teacher teacher: teachers) {
            if(teacherName.equals(teacher.toString())) {
                return teacher;
            }
        }

        return null;
    }


}
