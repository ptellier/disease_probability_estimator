package persistance;

import model.Disease;
import model.Study;
import model.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    static final private String filePath = "data/JsonWriterTest.json";
    static final private String invalidFilePath = "data/what\\illegal:fileName.json\"";
    JsonWriter invalidJsonWriter;
    JsonWriter jsonWriter1;
    private ArrayList<Study> expectedStudies;
    private ArrayList<Study> emptyStudies;

    @BeforeEach
    void setup() {
        jsonWriter1 = new JsonWriter(filePath);
        invalidJsonWriter = new JsonWriter(invalidFilePath);

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

        expectedStudies = new ArrayList<>(Arrays.asList(study1, study2, study3));
        emptyStudies = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals("data/JsonWriterTest.json", jsonWriter1.getFilePath());
        assertEquals("data/what\\illegal:fileName.json\"", invalidJsonWriter.getFilePath());
    }

    @Test
    void testWriteStudiesToFileThrowsFileNotFoundException() {
        try {
            invalidJsonWriter.openFile();
            fail();
        } catch (FileNotFoundException e) {
            //pass
        }
    }

    @Test
    void testWriteStudiesToFileWithEmptyStudies() {
        try {
            jsonWriter1.openFile();
            jsonWriter1.writeStudiesToFile(emptyStudies);
            jsonWriter1.closeFile();

            JsonReader jsonReader = new JsonReader(filePath);
            ArrayList<Study> studiesRead = jsonReader.readStudiesFromFile();
            JsonReaderTest.assertStudiesEqual(emptyStudies, studiesRead);
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriteStudiesToFile() {
        try {
            jsonWriter1.openFile();
            jsonWriter1.writeStudiesToFile(expectedStudies);
            jsonWriter1.closeFile();

            JsonReader jsonReader = new JsonReader(filePath);
            ArrayList<Study> studiesRead = jsonReader.readStudiesFromFile();
            JsonReaderTest.assertStudiesEqual(expectedStudies, studiesRead);
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
