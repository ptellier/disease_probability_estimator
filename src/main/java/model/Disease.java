package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Represents a Disease with a name, the numbers of people in the study affected by the disease,
//and the number of people with the disease with each symptom.
public class Disease {

    private String name;
    private int affected;
    private ArrayList<Symptom> symptoms;
    private double prob;

    //EFFECTS: Creates a disease with a name (e.g. "flu") and symptoms with associated probability
    public Disease(String name, int affected, ArrayList<Symptom> symptoms) {
        this.name = name;
        this.symptoms = symptoms;
        this.affected = affected;
    }

    //EFFECTS: Creates a disease with a name (e.g. "flu"), no symptoms yet,
    // and the number study participants with the disease
    public Disease(String name, int affected) {
        this.name = name;
        this.affected = affected;
        this.symptoms = new ArrayList<>();
    }

    //EFFECTS: adds a symptom to the disease
    //MODIFIES: this
    public void addSymptom(Symptom symptom) {
        symptoms.add(symptom);
    }

    //EFFECTS: adds a List of symptoms to the disease
    //MODIFIES: this
    public void addAllSymptoms(List<Symptom> symptoms) {
        this.symptoms.addAll(symptoms);
    }


    //EFFECTS: finds and sets the probability of having the disease from the list of symptoms
    //REQUIRES: posSymptomNames are spelled the same as those for symptom Names in disease
    //MODIFIES: this
    public void findProb(ArrayList<String> posSymptomNames) {
        double rsfProb = 1;
        for (Symptom symptom : symptoms) {
            if (posSymptomNames.contains(symptom.getName())) {
                rsfProb *= symptom.getAffected() / (double) affected;
            } else {
                rsfProb *= (affected - symptom.getAffected()) / (double) affected;
            }
        }
        prob = rsfProb;
    }

    //EFFECTS: returns the Disease data as a JSON object
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        for (Symptom symptom : symptoms) {
            jsonArray.put(symptom.toJson());
        }
        json.put("name", name);
        json.put("affected", affected);
        json.put("symptoms", jsonArray);
        return json;
    }

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    public String getName() {
        return name;
    }

    public int getAffected() {
        return affected;
    }

    public double getProb() {
        return prob;
    }
}
