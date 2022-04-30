package persistance;

import model.Disease;
import model.Study;
import model.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.StudiesSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudiesSingletonTest {

    StudiesSingleton singleton;
    static final private String filePathStudies1 = "data/StudiesSingletonTest.json";
    static final private String filePathEmpty = "data/StudiesSingletonTestEmpty.json";
    static final private String invalidFilePath = "data/StudiesSingletonTestEmptyInvalidFilePath.json";

    private ArrayList<Study> studies1;
    private Study study1;
    private Study study2;
    private Study study3;
    private ArrayList<Study> emptyStudies;

    @BeforeEach
    void setup() {
        singleton = StudiesSingleton.makeInstance();

        Symptom A1 = new Symptom("same", 400);
        Disease A = new Disease("A", 500, new ArrayList<>(Arrays.asList(A1)));
        study1 = new Study(600, new ArrayList<>(Arrays.asList(A)));

        Symptom B1 = new Symptom("B1", 100);
        Symptom B2 = new Symptom("B2", 200);
        Disease B = new Disease("B", 300, new ArrayList<>(Arrays.asList(B1, B2)));
        Symptom C1 = new Symptom("C1", 1700);
        Symptom C2 = new Symptom("same", 1800);
        Disease C = new Disease("C", 1900, new ArrayList<>(Arrays.asList(C1, C2)));
        study2 = new Study(2000, new ArrayList<>(Arrays.asList(B, C)));

        Symptom D1 = new Symptom("D1", 1000);
        Disease D = new Disease("D", 1100, new ArrayList<>(Arrays.asList(D1)));
        study3 = new Study(1200, new ArrayList<>(Arrays.asList(D)));

        studies1 = new ArrayList<>(Arrays.asList(study1, study2));
        emptyStudies = new ArrayList<>();
    }

    @Test
    void makeInstanceTest() {
        JsonReaderTest.assertStudiesEqual(emptyStudies, StudiesSingleton.getStudies());
    }

    @Test
    void switchFileTest() {
        singleton.switchFiles(filePathStudies1);
        JsonReaderTest.assertStudiesEqual(studies1, StudiesSingleton.getStudies());
    }

    @Test
    void switchFileToEmptyTest() {
        singleton.switchFiles(filePathEmpty);
        JsonReaderTest.assertStudiesEqual(emptyStudies, StudiesSingleton.getStudies());
    }

    @Test
    void switchFileToInvalidTest() {
        singleton.switchFiles(filePathEmpty);
        singleton.switchFiles(invalidFilePath);
        JsonReaderTest.assertStudiesEqual(new ArrayList<>(), StudiesSingleton.getStudies());
    }

    @Test
    void saveCurrentStudiesCatchesFileNotFound() {
        singleton.switchFiles(filePathEmpty);
        singleton.switchFiles(invalidFilePath);
        singleton.saveCurrentStudies();
        JsonReaderTest.assertStudiesEqual(new ArrayList<>(), StudiesSingleton.getStudies());
    }

    @Test
    void addStudyToSaveFileAndRemoveAllTest() {
        singleton.switchFiles(filePathStudies1);
        ArrayList<Study> actual1 = StudiesSingleton.getStudies();
        JsonReaderTest.assertStudiesEqual(new ArrayList<>(Arrays.asList(study1, study2)), actual1);

        singleton.addStudyToSaveFile(study3);
        ArrayList<Study> actual2 = StudiesSingleton.getStudies();
        JsonReaderTest.assertStudiesEqual(new ArrayList<>(Arrays.asList(study1, study2, study3)), actual2);

        singleton.removeAll(new ArrayList<>(Arrays.asList(actual2.get(1), actual2.get(2))));
        ArrayList<Study> actual3 = StudiesSingleton.getStudies();
        JsonReaderTest.assertStudiesEqual(new ArrayList<>(Arrays.asList(study1)), actual3);

        singleton.addStudyToSaveFile(study2);
        ArrayList<Study> actual4 = StudiesSingleton.getStudies();
        JsonReaderTest.assertStudiesEqual(new ArrayList<>(Arrays.asList(study1, study2)), actual4);
    }

    @Test
    void getUniqueSymptomNamesTest() {
        Set<String> uniqueNames = new HashSet<>(StudiesSingleton.getUniqueSymptomNames());
        assertEquals(new HashSet<>(Arrays.asList("same" ,"B1", "B2", "C1" )), uniqueNames);
    }
}
