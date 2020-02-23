import model.*;
import model.exceptions.NoTeachRequirementsSetException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private List<Teacher> teachers;
    private List<Course> courses;
    private List<ClassDirector> courseDirectors;
    private PTTDirector pttDirector;
    private Admin admin;
    private Scanner scanner;

    public Controller() {
        InputOutput io = InputOutput.getInstance();
        DataWrapper dataWrapper = io.readFromJSON();
        teachers = dataWrapper.getTeachers();
        courses = dataWrapper.getCourses();
        courseDirectors = dataWrapper.getClassDirectors();
        pttDirector = dataWrapper.getPttDirector();
        admin = dataWrapper.getAdmin();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Pick user:");
            System.out.println("-1 -> QUIT");
            System.out.println("1 -> Admin");
            System.out.println("2 -> PTTDirector");
            System.out.println("3 -> ClassDirector");
            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
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
                        classDirectorSubMenu();
                        break;

                    default:
                        continue;
                }
            }

        }
    }

    public void adminSubMenu() {
        while (true) {
            System.out.println("Pick action:");
            System.out.println("-1 -> QUIT");
            System.out.println("0 -> Go Back");
            System.out.println("1 -> Find and propose qualified teachers for a course");
            System.out.println("2 -> Mark teacher training as completed");
            int userInput;
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
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
            }
        }
    }

    public void suitableTeachersSubMenu() {
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
                        intUserInput--;
                        if (intUserInput >= -1 && intUserInput < suitableTeachers.size()) {
                            break;
                        }
                    }
                    System.out.println("Incorrect input. Try again.");
                }
                if (intUserInput > -1) {
                    Teacher proposedTeacher = suitableTeachers.get(intUserInput);
                    try {
                        System.out.println("Proposal successful.");
                        admin.proposeTeacher(course, proposedTeacher);
                    } catch (Exception e) {
                        System.out.println("Proposal unsuccessful.");
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public void pttDirectorSubMenu() {

    }

    public void classDirectorSubMenu() {

    }

    public void quit() {
        DataWrapper dataWrapper = new DataWrapper(teachers, courses, courseDirectors, admin, pttDirector);
        System.exit(0);
    }

    private Course getCourse(String name) {

        for (Course course : courses) {
            if (course.toString().equals(name)) {
                return course;
            }
        }
        return null;
    }
}
