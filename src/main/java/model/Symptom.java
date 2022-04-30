package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//Represents a symptom with a name and the number of people with a disease in a study that were affected
public class Symptom {

    private final String name;
    private final int affected; //number of people with a disease displaying the symptom

    //EFFECTS: creates a symptom with a name and number of people for which a symptom affects for a disease
    public Symptom(String name, int affected) {
        this.name = name;
        this.affected = affected;
    }

    //EFFECTS: filters out duplicate Strings from ArrayList
    //MODIFIES: symptomNames
    public static void filterUniqueSymptomNames(ArrayList<String> symptomNames) {
        Set<String> stringSet = new HashSet<>(symptomNames);
        symptomNames.clear();
        symptomNames.addAll(stringSet);
    }

    //EFFECTS: returns the Symptom data as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("affected", affected);
        return json;
    }

    public String getName() {
        return name;
    }

    public int getAffected() {
        return affected;
    }

}
