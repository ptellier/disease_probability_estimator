package persistence;

import model.Study;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

// A file writer used to write studies data to a .json file
public class JsonWriter {

    private String filePath;
    private PrintWriter printWriter;

    //EFFECTS: creates JsonWriter with the path to where the file will be written
    //REFERENCE: adapted from JsonSerializationDemo
    public JsonWriter(String filePath) {
        this.filePath = filePath;

    }

    //EFFECTS: opens filePath file for writing and throws FileNotFoundException if it cannot create file
    //MODIFIES: this
    //REFERENCE: adapted from JsonSerializationDemo
    public void openFile() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(filePath));
    }

    //EFFECTS: record data from list of studies in a file
    //MODIFIES: this
    //REQUIRES: openFile() has been called to open the file for writing
    //REFERENCE: adapted from JsonSerializationDemo
    public void writeStudiesToFile(ArrayList<Study> studies) {
        printWriter.print(studiesToJson(studies).toString());

    }

    //EFFECTS: closes the file in filePath
    //MODIFIES: this
    //REQUIRES: openFile() has been called to open the file for writing
    //REFERENCE: adapted from JsonSerializationDemo
    public void closeFile() {
        printWriter.close();
    }

    //EFFECTS: returns the data in studies as a JSONObject
    public JSONObject studiesToJson(ArrayList<Study> studies) {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        for (Study study : studies) {
            jsonArray.put(study.toJson());
        }
        json.put("studies", jsonArray);
        return json;
    }

    public String getFilePath() {
        return filePath;
    }
}
