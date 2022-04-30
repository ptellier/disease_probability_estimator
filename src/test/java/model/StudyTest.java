package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudyTest {

    private static final double DELTA = 0.0001;

    Study studyFluCough;
    Study studyEmpty;
    Study studyJustSampleSize;
    private Disease flu;
    private Symptom coughFlu;
    private Symptom feverFlu;
    private Disease cold;
    private Symptom coughCold;
    private Symptom feverCold;
    private Symptom snifflesCold;

    @BeforeEach
    public void setup() {
        flu = new Disease("flu", 300);
        coughFlu = new Symptom("cough", 50);
        feverFlu = new Symptom("fever", 200);
        flu.addSymptom(coughFlu);
        flu.addSymptom(feverFlu);

        cold = new Disease("cold", 500);
        coughCold = new Symptom("cough", 400);
        feverCold = new Symptom("fever", 100);
        snifflesCold = new Symptom("sniffles", 450);
        cold.addSymptom(coughCold);
        cold.addSymptom(feverCold);
        cold.addSymptom(snifflesCold);

        studyFluCough = new Study(1000, new ArrayList<>(Arrays.asList(flu, cold)));
        studyEmpty = new Study(1000, new ArrayList<>());
        studyJustSampleSize = new Study(1200);
    }

    @Test
    public void testStudySampleSizeAndDiseases() {
        assertEquals(1000, studyEmpty.getSampleSize());
        assertEquals(new ArrayList<>(), studyEmpty.getDiseases());
        assertEquals(1000, studyFluCough.getSampleSize());
        assertEquals(new ArrayList<>(Arrays.asList(flu, cold)), studyFluCough.getDiseases());
    }

    @Test
    public void testStudyJustSampleSize() {
        assertEquals(1200, studyJustSampleSize.getSampleSize());
        assertEquals(new ArrayList<>(), studyJustSampleSize.getDiseases());
    }

    @Test
    public void testAddDisease() {
        assertEquals(new ArrayList<>(), studyEmpty.getDiseases());
        studyEmpty.addDisease(flu);
        assertEquals(new ArrayList<>(Arrays.asList(flu)), studyEmpty.getDiseases());
        studyEmpty.addDisease(cold);
        assertEquals(new ArrayList<>(Arrays.asList(flu, cold)), studyEmpty.getDiseases());
    }

    @Test
    public void testFindAllProbs() {
        studyFluCough.findAllProbs(new ArrayList<>(Arrays.asList("cough", "fever")));
        double fluProb = studyFluCough.getDiseases().get(0).getProb();
        double coldProb =  studyFluCough.getDiseases().get(1).getProb();
        flu.findProb(new ArrayList<>(Arrays.asList("cough", "fever")));
        cold.findProb(new ArrayList<>(Arrays.asList("cough", "fever")));
        assertEquals(flu.getProb(), fluProb, DELTA);
        assertEquals(cold.getProb(), coldProb, DELTA);
    }

    @Test
    public void testGetAllSymptoms() {
        assertEquals(new ArrayList<>(), studyEmpty.getAllSymptoms());
        assertEquals(new ArrayList<>(Arrays.asList("cough", "fever", "sniffles")), studyFluCough.getAllSymptoms());
    }

    @Test
    public void testGetAllDiseaseNames() {
        assertEquals(new ArrayList<>(), studyEmpty.getAllDiseaseNames());
        assertEquals(new ArrayList<>(Arrays.asList("flu", "cold")), studyFluCough.getAllDiseaseNames());
    }
}
