import model.*;
import model.exceptions.NoProposedTeacherException;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws Exception {
//        Teacher t1 = new Teacher("John Smith");
//        Teacher t2 = new Teacher("Michael Adams");
//        Teacher t3 = new Teacher("Mac White");
//        Teacher t4 = new Teacher("Daniel Ivanov");
//        Teacher t5 = new Teacher("Amy Williams");
//
//        Course c1 = new Course("Maths",1,"Maths Teaching Professional");
//        Course c2 = new Course("Psychology",2,"Psychology Teaching Professional");
//        Course c3 = new Course("Arts",1,"Arts Teaching Professional");
//        Course c4 = new Course("Economics",2,"Economics Teaching Professional");
//        Course c5 = new Course("Geography",2,"Geography Teaching Professional");
//        LinkedList<Teacher> teachers = new LinkedList<>();
//        LinkedList<Course> courses = new LinkedList<>();
//
//
//
//
//        teachers.add(t1);
//        teachers.add(t2);
//        teachers.add(t3);
//        teachers.add(t4);
//        teachers.add(t5);
//        courses.add(c1);
//        courses.add(c2);
//        courses.add(c3);
//        courses.add(c4);
//        courses.add(c5);
//
//
//
//        TeachRequestMap teachRequestMap = new TeachRequestMap();
//
//        CourseDirector cd1 = new CourseDirector("Filip Marinov",teachRequestMap);
//        cd1.setDirectedCourse(c1);
//        CourseDirector cd2 = new CourseDirector("Tereza Buckova",teachRequestMap);
//        cd2.setDirectedCourse(c2);
//        CourseDirector cd3 = new CourseDirector("James Scott",teachRequestMap);
//        cd3.setDirectedCourse(c3);
//        CourseDirector cd4 = new CourseDirector("Jamie Links",teachRequestMap);
//        cd4.setDirectedCourse(c4);
//        CourseDirector cd5 = new CourseDirector("Adam Stevens",teachRequestMap);
//        cd5.setDirectedCourse(c5);
//
//
//        LinkedList<CourseDirector> classDirectors = new LinkedList<>();
//        classDirectors.add(cd1);
//        classDirectors.add(cd2);
//        classDirectors.add(cd3);
//        classDirectors.add(cd4);
//        classDirectors.add(cd5);
//
//
//        PTTDirector pd = new PTTDirector("Nade Dimitrova",teachRequestMap);
//        Admin admin = new Admin("Vince Antov",teachRequestMap);
//
//
//        LinkedList<String> skills1 = new LinkedList<>();
//        LinkedList<String> skills2 = new LinkedList<>();
//        LinkedList<String> skills3 = new LinkedList<>();
//        LinkedList<String> skills4 = new LinkedList<>();
//        LinkedList<String> skills5 = new LinkedList<>();
//
//        skills1.add("PhD Mathematics");
//        skills1.add("Industry Experience");
//        skills2.add("PhD Psychology");
//        skills2.add("Research Experience");
//        skills3.add("PhD Arts");
//        skills4.add("PhD Economics");
//        skills4.add("Industry Experience");
//        skills5.add("PhD Geography");
//        skills5.add("Industry Experience");
//        skills5.add("Published Paper");
//
//
//
//        //setting teacher skills
//        t1.setSkills(skills1);
//        t2.setSkills(skills2);
//        t3.setSkills(skills3);
//        t4.setSkills(skills4);
//        t5.setSkills(skills5);
//
//
//
//        DataWrapper dataWrapper = new DataWrapper(teachers,courses,classDirectors,admin,pd);
//        InputOutput io = InputOutput.getInstance();
//        io.writeToJSON(dataWrapper);



        Controller c = new Controller();
        c.start();
    }
}
