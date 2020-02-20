package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class InputOutput {
    private static InputOutput single_instance = null;
    private static final String FILE_PATH_STRING = System.getProperty("user.dir") + "\\src\\main\\resources\\staffMap.json";

    private InputOutput() {
    }

    public static InputOutput getInstance() {
        if (single_instance == null)
            single_instance = new InputOutput();
        return single_instance;
    }

    public void writeToJSON(ArrayList<CourseDirector> courseDirectors, PTTDirector pttDirector, Admin admin) {

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> staffMap = new HashMap<>();
        staffMap.put("CourseDirector", courseDirectors);
        staffMap.put("PTTDirector", pttDirector);
        staffMap.put("Admin", admin);


        try {
            // Java objects to JSON file
            mapper.writeValue(new File(FILE_PATH_STRING), staffMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> staffMap = new HashMap<>();
        try {
            // JSON file to Java object
            staffMap = mapper.readValue(new File(FILE_PATH_STRING), StaffMap.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


