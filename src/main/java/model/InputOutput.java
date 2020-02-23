package model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class InputOutput {
    private static InputOutput single_instance = new InputOutput();
    private static final String FILE_PATH_STRING = System.getProperty("user.dir") + "\\src\\main\\resources\\staff.json";

    private InputOutput() {
    }

    public static InputOutput getInstance() {
        return single_instance;
    }

    public void writeToJSON(DataWrapper dataWrapper) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();


        try {

            mapper.writeValue(new File(FILE_PATH_STRING), dataWrapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public DataWrapper readFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        DataWrapper dataWrapper = null;
        try {

            dataWrapper = mapper.readValue(new File(FILE_PATH_STRING), DataWrapper.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataWrapper;
    }
}

