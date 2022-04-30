package persistence;

import model.Event;
import model.EventLog;
import model.Study;
import model.Symptom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

// singleton (class there is always a single instance of) that contains all the study data for the program
public class StudiesSingleton extends Subject {

    private static StudiesSingleton singleton;
    private static ArrayList<Study> studies;
    //private static final String filePath = "data/studyData.json";
    JsonWriter jsonWriter;
    JsonReader jsonReader;

    // EFFECTS: constructs a StudiesSingleton with empty studies ArrayList
    private StudiesSingleton() {
        studies = new ArrayList<>();
    }

    // EFFECTS: retrieves/creates the single instance of studiesSingleton
    // MODIFIES: this (when creating this the first time called)
    public static StudiesSingleton makeInstance() {
        if (singleton == null) {
            singleton = new StudiesSingleton();
        }
        return singleton;
    }

    // EFFECTS: adds a study to studies in the Singleton and notifies observers
    // MODIFIES: this
    public void addStudyToSaveFile(Study study) {
        assert study != null : "illegal argument null for addStudyToSaveFile";
        studies.add(study);
        saveCurrentStudies();
        notifyObservers();
        EventLog.getInstance().logEvent(new Event("added Study with " + study.getAllDiseaseNames()));
    }

    // EFFECTS: removes studiesToRemove from studies in the Singleton and notifies observers
    // MODIFIES: this
    public void removeAll(Collection<Study> studiesToRemove) {
        assert jsonWriter != null && jsonReader != null : "must call setFile(String fileName) on StudiesSingleton";
        studies.removeAll(studiesToRemove);
        saveCurrentStudies();
        notifyObservers();
        for (Study study: studiesToRemove) {
            EventLog.getInstance().logEvent(new Event("removed study with " + study.getAllDiseaseNames()));
        }
    }

    // EFFECTS: saves studies in the Singleton to file specified by filepath. Fails if filepath is invalid
    // MODIFIES: this
    public void saveCurrentStudies() {
        assert jsonWriter != null && jsonReader != null : "must call setFile(String fileName) on StudiesSingleton";
        try {
            jsonWriter.openFile();
        } catch (FileNotFoundException e) {
            System.err.printf("Could not open file path %s\n", jsonWriter.getFilePath());
        }
        jsonWriter.writeStudiesToFile(studies);
        jsonWriter.closeFile();
    }

    // EFFECTS: returns the symptom names for all diseases in the study with no duplicates
    public static ArrayList<String> getUniqueSymptomNames() {
        ArrayList<String> symptomNames = new ArrayList<>();
        for (Study study: StudiesSingleton.makeInstance().getStudies()) {
            symptomNames.addAll(study.getAllSymptoms());
        }
        Symptom.filterUniqueSymptomNames(symptomNames);
        return symptomNames;
    }

    // EFFECTS: sets JsonWriter and JsonReader that have specified file path,
    //          and has studies read from filepath. Fails if filepath is invalid
    // MODIFIES: this
    public void switchFiles(String filePath) {
        jsonWriter = new JsonWriter(filePath);
        jsonReader = new JsonReader(filePath);
        try {
            studies = jsonReader.readStudiesFromFile();
        } catch (IOException e) {
            System.err.printf("could not read file at %s \n", filePath);
        }
    }

    public static ArrayList<Study> getStudies() {
        return studies;
    }
}

// Do I need to have a requires clause if I have assert???
// Is Singleton okay to use??
