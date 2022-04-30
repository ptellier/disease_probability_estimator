package persistence;

import model.Disease;
import model.Study;
import model.Symptom;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// A file reader used to write studies data to a .json file
public class JsonReader {

    private String filePath;

    //EFFECTS: constructs reader to read from source file
    //REFERENCE: adapted from JsonSerializationDemo
    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    //EFFECTS: reads studies from file at filePath and returns it;
    // throws IOException if an error occurs reading data from file
    //REFERENCE: adapted from JsonSerializationDemo
    public ArrayList<Study> readStudiesFromFile() throws IOException {
        String jsonData = readFile(filePath);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudies(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    //REFERENCE: adapted from JsonSerializationDemo
    private String readFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses Studies from JSON object and adds it to studies
    //REFERENCE: adapted from JsonSerializationDemo
    private ArrayList<Study> parseStudies(JSONObject jsonObject) {
        ArrayList<Study> studies = new ArrayList<>();
        JSONArray studiesJsonArray = jsonObject.getJSONArray("studies");
        for (Object studyObject : studiesJsonArray) {
            JSONObject studyJsonObject = (JSONObject) studyObject;
            int sampleSize = studyJsonObject.getInt("sampleSize");
            Study nextStudy = new Study(sampleSize);
            addDiseases(nextStudy, studyJsonObject);
            studies.add(nextStudy);
        }
        return studies;
    }

    //EFFECTS: parses Diseases from JSON object and adds them to Study
    //MODIFIES: study
    //REFERENCE: adapted from JsonSerializationDemo
    private void addDiseases(Study study, JSONObject jsonObject) {
        JSONArray diseaseJsonArray = jsonObject.getJSONArray("diseases");
        for (Object diseaseJsonObject : diseaseJsonArray) {
            addDisease(study, (JSONObject) diseaseJsonObject);
        }
    }

    //EFFECTS: parses Disease from JSON object and adds it to Study
    //MODIFIES: study
    //REFERENCE: adapted from JsonSerializationDemo
    private void addDisease(Study study, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int affected = jsonObject.getInt("affected");
        Disease disease = new Disease(name, affected);
        addSymptoms(disease, jsonObject);
        study.addDisease(disease);
    }

    //EFFECTS: parses Symptoms from JSON object and adds them to disease
    //MODIFIES: disease
    //REFERENCE: adapted from JsonSerializationDemo
    private void addSymptoms(Disease disease, JSONObject jsonObject) {
        JSONArray symptomJsonArray = jsonObject.getJSONArray("symptoms");
        for (Object symptomJsonObject : symptomJsonArray) {
            addSymptom(disease, (JSONObject) symptomJsonObject);
        }
    }

    //EFFECTS: parses Symptom from JSON object and adds it to disease
    //MODIFIES: disease
    //REFERENCE: adapted from JsonSerializationDemo
    private void addSymptom(Disease disease, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int affected = jsonObject.getInt("affected");
        disease.addSymptom(new Symptom(name, affected));
    }

    public String getFilePath() {
        return filePath;
    }
}
