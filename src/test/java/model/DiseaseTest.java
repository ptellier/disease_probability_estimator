package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiseaseTest {

    private static final double DELTA = 0.0001;

    private Disease fluNoSymptoms;
    private Disease fluWithSymptoms;
    private Disease fluConstructorWithSymptoms;
    private Symptom cough;
    private Symptom fever;

    @BeforeEach
    public void setup() {
        cough = new Symptom("cough", 50);
        fever = new Symptom("fever", 200);
        fluNoSymptoms = new Disease("flu", 300);
        fluWithSymptoms = new Disease("flu", 300);
        fluConstructorWithSymptoms = new Disease("flu", 300, new ArrayList<>(Arrays.asList(cough, fever)));
        fluWithSymptoms.addSymptom(cough);
        fluWithSymptoms.addSymptom(fever);
    }

    @Test
    public void testDiseaseConstructorWithoutSymptoms() {
        assertEquals("flu", fluNoSymptoms.getName());
        assertEquals(300, fluNoSymptoms.getAffected());
        assertEquals(new ArrayList<>(), fluNoSymptoms.getSymptoms());
    }

    @Test
    public void testDiseaseConstructorWithSymptoms() {
        assertEquals("flu", fluConstructorWithSymptoms.getName());
        assertEquals(300, fluConstructorWithSymptoms.getAffected());
        assertEquals(new ArrayList<>(Arrays.asList(cough, fever)), fluConstructorWithSymptoms.getSymptoms());
    }

    @Test
    public void testAddSymptom() {
        assertEquals(new ArrayList<>(), fluNoSymptoms.getSymptoms());
        fluNoSymptoms.addSymptom(cough);
        assertEquals(new ArrayList<>(Arrays.asList(cough)), fluNoSymptoms.getSymptoms());
        fluNoSymptoms.addSymptom(fever);
        assertEquals(new ArrayList<>(Arrays.asList(cough, fever)), fluNoSymptoms.getSymptoms());
    }

    @Test
    public void testAddAllSymptoms() {
        fluNoSymptoms.addAllSymptoms(Arrays.asList(cough, fever));
        assertEquals(new ArrayList<>(Arrays.asList(cough, fever)), fluNoSymptoms.getSymptoms());
    }

    @Test
    public void testFindProb() {
        fluWithSymptoms.findProb(new ArrayList<>(Arrays.asList("cough","fever")));
        assertEquals((50 / (double) 300) * (200 / (double) 300), fluWithSymptoms.getProb(),  DELTA);

        fluWithSymptoms.findProb(new ArrayList<>(Arrays.asList("cough")));
        assertEquals((50 / (double) 300) * ((300 - 200) / (double) 300), fluWithSymptoms.getProb(), DELTA);

        fluWithSymptoms.findProb(new ArrayList<>(Arrays.asList("fever")));
        assertEquals(((300 - 50) / (double) 300) * (200 / (double) 300), fluWithSymptoms.getProb(), DELTA);

        fluWithSymptoms.findProb(new ArrayList<>());
        assertEquals(((300 - 50) / (double) 300) * ((300 - 200) / (double) 300), fluWithSymptoms.getProb(), DELTA);
    }
}
