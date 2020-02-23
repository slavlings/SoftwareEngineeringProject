import model.*;
import model.exceptions.NoProposedTeacherException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

public class Main {
    private static final String FILE_PATH_STRING = System.getProperty("user.dir") + "\\src\\main\\resources\\staff.json";

    public static void main(String[] args) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Teacher t1 = new Teacher("John");
        Teacher t2 = new Teacher("Michael");


        Course c1 = new Course("Maths",1,"Student Mental Health");
        Course c2 = new Course("Psychology",2,"Student Mental Health");
        LinkedList<Teacher> teachers = new LinkedList<>();
        LinkedList<Course> courses = new LinkedList<>();

        teachers.add(t1);
        teachers.add(t2);
        gson.toJson(teachers);
        courses.add(c1);
        courses.add(c2);
        gson.toJson(courses);

        TeachRequestMap teachRequestMap = new TeachRequestMap();

        ClassDirector cd1 = new ClassDirector("Filip",teachRequestMap);
        cd1.setDirectedCourse(c1);
        ClassDirector cd2 = new ClassDirector("Tereza",teachRequestMap);
        cd2.setDirectedCourse(c2);

        LinkedList<ClassDirector> classDirectors = new LinkedList<>();
        classDirectors.add(cd1);
        classDirectors.add(cd2);
        gson.toJson(classDirectors);

        PTTDirector pd = new PTTDirector("Nade",teachRequestMap);
        gson.toJson(pd);
        Admin admin = new Admin("Vince",teachRequestMap);
        gson.toJson(admin);

        LinkedList<String> teachRequirements1 = new LinkedList<>();
        LinkedList<String> teachRequirements2 = new LinkedList<>();

        teachRequirements1.add("PhD Mathematics");
        teachRequirements2.add("PhD Psychology");
        teachRequirements2.add("Research Experience");

        LinkedList<String> skills1 = new LinkedList<>();
        LinkedList<String> skills2 = new LinkedList<>();

        skills1.add("PhD Mathematics");
        skills2.add("PhD Psychology");
        skills2.add("Research Experience");

        //setting teacher skills
        t1.setSkills(skills1);
        t2.setSkills(skills2);

        //setting teachReqs
        cd1.setCourseTeachRequirements(teachRequirements1);
        cd2.setCourseTeachRequirements(teachRequirements2);

        //add teachReqs
        cd1.addTeachRequest();
        cd2.addTeachRequest();

        //find suitable teachers
        LinkedList<Teacher> suitableTeachers1 = admin.findSuitableStaff(c1,teachers);
        LinkedList<Teacher> suitableTeachers2 = admin.findSuitableStaff(c2,teachers);
        Teacher suitableTeacher1 = suitableTeachers1.get(0);
        Teacher suitableTeacher2 = suitableTeachers2.get(0);

        //propose teachers
        admin.proposeTeacher(c1,suitableTeacher1);
        admin.proposeTeacher(c2,suitableTeacher2);

        //set Teacher training
        admin.addTraining(suitableTeacher1,"Student Mental Health");
        admin.addTraining(suitableTeacher2,"Student Mental Health");

        //complete Teacher training
        admin.completeTraining(suitableTeacher1,"Student Mental Health");
        admin.completeTraining(suitableTeacher2,"Student Mental Health");

        //approve
        pd.approveTeachRequest(c1);
        try {
            pd.approveTeachRequest(c2);
        }catch (NoProposedTeacherException e) {

        }

//        try {
//            String a = gson.toJson(t2);
//            System.out.println(a);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        filewriting
        try (FileWriter writer = new FileWriter(FILE_PATH_STRING)) {
            gson.toJson(t2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputOutputGSON.teacherRead();

    }

}
