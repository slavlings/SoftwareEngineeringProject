package model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Singleton class for writing to and reading from the staff.json file.
 */
public class InputOutput {
    private static InputOutput single_instance = new InputOutput();
    private static final String FILE_PATH_STRING = System.getProperty("user.dir") + "\\src\\main\\resources\\staff.json";
    private ObjectMapper mapper;

    private InputOutput() {
        mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
    }

    /**
     * Method for getting the instance of this singleton class.
     * @return singleton instance
     */
    public static InputOutput getInstance() {
        return single_instance;
    }

    /**
     * Method for writing to the staff.json file.
     * @param dataWrapper object to be written to the file
     */
    public void writeToJSON(DataWrapper dataWrapper) {
        try {
            mapper.writeValue(new File(FILE_PATH_STRING), dataWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for reading from the staff.json file.
     * @return a DataWrapper object read from the staff.json file
     */
    public DataWrapper readFromJSON() {
        DataWrapper dataWrapper = null;
        try {
            dataWrapper = mapper.readValue(new File(FILE_PATH_STRING), DataWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataWrapper;
    }
}

