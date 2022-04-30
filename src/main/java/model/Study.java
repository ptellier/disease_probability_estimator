package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//Represents a study of people with different diseases. Has the number of people in the study, the numbers of people
//with the different diseases, and the numbers of people with each symptom per disease.
public class Study {

    private int sampleSize;
    private ArrayList<Disease> diseases;

    //EFFECTS: construct the data for a study with number of people in the study and the diseases studied that includes
    //the number of people affected by the diseases symptoms
    public Study(int sampleSize, ArrayList<Disease> diseases) {
        this.sampleSize = sampleSize;
        this.diseases = diseases;
    }

    //EFFECTS: construct the data for a study with number of people in the study
    public Study(int sampleSize) {
        this.sampleSize = sampleSize;
        this.diseases = new ArrayList<>();
    }

    //EFFECTS: adds a disease to the study
    //MODIFIES: this
    public void addDisease(Disease disease) {
        diseases.add(disease);
    }

    //EFFECTS: finds the all probabilities that of having each disease based on positive symptom names
    //REQUIRES: posSymptomNames are spelled the same as those for symptom Names in the study
    //MODIFIES: this
    public void findAllProbs(ArrayList<String> posSymptomNames) {
        for (Disease disease : diseases) {
            disease.findProb(posSymptomNames);
        }
    }

    //EFFECTS: returns the study data as a JSON object
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        for (Disease disease : diseases) {
            jsonArray.put(disease.toJson());
        }
        json.put("sampleSize", sampleSize);
        json.put("diseases", jsonArray);
        return json;
    }

    //EFFECTS: returns the symptom names for all diseases in the study with no duplicates
    public ArrayList<String> getAllSymptoms() {
        ArrayList<String> symptomNames = new ArrayList<>();
        for (Disease disease : diseases) {
            for (Symptom symptom : disease.getSymptoms()) {
                String symptomName = symptom.getName();
                if (!symptomNames.contains(symptomName)) {
                    symptomNames.add(symptomName);
                }
            }
        }
        return symptomNames;
    }

    //EFFECTS: returns the name of all diseases in this study
    public ArrayList<String> getAllDiseaseNames() {
        ArrayList<String> diseaseNames = new ArrayList<>();
        for (Disease disease : diseases) {
            diseaseNames.add(disease.getName());
        }
        return diseaseNames;
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public int getSampleSize() {
        return sampleSize;
    }

}
