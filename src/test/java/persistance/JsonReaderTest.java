package persistance;

import model.Disease;
import model.Study;
import model.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    static final private String filePathStudies1 = "data/JsonReaderTest.json";
    static final private String filePathEmpty = "data/JsonReaderTestEmpty.json";
    static final private String invalidFilePath = "data/JsonInvalidFilePath.json";
    private JsonReader invalidJsonReader;
    private JsonReader jsonReaderEmpty;
    private JsonReader jsonReader1;
    private ArrayList<Study> studies1;
    private ArrayList<Study> emptyStudies;

    @BeforeEach
    void setup() {
        jsonReader1 = new JsonReader(filePathStudies1);
        jsonReaderEmpty = new JsonReader(filePathEmpty);
        invalidJsonReader = new JsonReader(invalidFilePath);
        Symptom A1 = new Symptom("A1", 400);
        Disease A = new Disease("A", 500, new ArrayList<>(Arrays.asList(A1)));
        Study study1 = new Study(600, new ArrayList<>(Arrays.asList(A)));

        Symptom B1 = new Symptom("B1", 100);
        Symptom B2 = new Symptom("B2", 200);
        Disease B = new Disease("B", 300, new ArrayList<>(Arrays.asList(B1, B2)));
        Symptom C1 = new Symptom("C1", 1700);
        Symptom C2 = new Symptom("C2", 1800);
        Disease C = new Disease("C", 1900, new ArrayList<>(Arrays.asList(C1, C2)));
        Study study2 = new Study(2000, new ArrayList<>(Arrays.asList(B, C)));

        Symptom D1 = new Symptom("D1", 1000);
        Disease D = new Disease("D", 1100, new ArrayList<>(Arrays.asList(D1)));
        Study study3 = new Study(1200, new ArrayList<>(Arrays.asList(D)));

        studies1 = new ArrayList<>(Arrays.asList(study1, study2, study3));
        emptyStudies = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals("data/JsonReaderTest.json", jsonReader1.getFilePath());
        assertEquals( "data/JsonInvalidFilePath.json", invalidJsonReader.getFilePath());
    }

    @Test
    void testReadStudiesFromFileWithEmptyStudies() {
        try {
            assertStudiesEqual(emptyStudies, jsonReaderEmpty.readStudiesFromFile());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testReadStudiesFromFile() {
        try {
            assertStudiesEqual(studies1, jsonReader1.readStudiesFromFile());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testReadStudiesFromFileThrowsIOException() {
        try {
            invalidJsonReader.readStudiesFromFile();
            fail();
        } catch (IOException e) {
            //pass
        } catch (Exception e) {
            fail();
        }
    }

    public static void assertStudiesEqual(ArrayList<Study> expected, ArrayList<Study> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertStudyEquals(expected.get(i), actual.get(i));
        }
    }

    public static void assertStudyEquals(Study expected, Study actual) {
        assertEquals(expected.getSampleSize(), actual.getSampleSize());
        assertEquals(expected.getDiseases().size(), actual.getDiseases().size());
        for (int i = 0; i < expected.getDiseases().size(); i++) {
            assertDiseaseEquals(expected.getDiseases().get(i), actual.getDiseases().get(i));
        }
    }

    public static void assertDiseaseEquals(Disease expected, Disease actual) {
        assertEquals(expected.getAffected(), actual.getAffected());
        assertEquals(expected.getSymptoms().size(), actual.getSymptoms().size());
        for (int i = 0; i < expected.getSymptoms().size(); i++) {
            assertSymptomEquals(expected.getSymptoms().get(i), actual.getSymptoms().get(i));
        }
    }

    public static void assertSymptomEquals(Symptom expected, Symptom actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAffected(), actual.getAffected());
    }
}
