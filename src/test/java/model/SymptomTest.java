package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymptomTest {

    private Symptom symptomCough;
    private ArrayList<String> symptomNames0;
    private ArrayList<String> symptomNames1;
    private ArrayList<String> symptomNames2;
    private ArrayList<String> symptomNames3;
    private ArrayList<String> symptomNames4;

    @BeforeEach
    public void setup() {
        symptomCough = new Symptom("cough", 300);
        symptomNames0 = new ArrayList<>();
        symptomNames1 = new ArrayList<>(Arrays.asList("symp1", "symp2", "symp3"));
        symptomNames2 = new ArrayList<>(Arrays.asList("symp1", "symp3", "symp2", "symp3"));
        symptomNames3 = new ArrayList<>(Arrays.asList("symp2", "symp1", "symp2", "symp3"));
        symptomNames4 = new ArrayList<>(Arrays.asList("symp1", "symp2", "symp2", "symp3"));
    }

    @Test
    public void testSymptom() {
        assertEquals("cough", symptomCough.getName());
        assertEquals(300, symptomCough.getAffected());
    }

    @Test
    public void testFilterUniqueSymptomNames() {
        Symptom.filterUniqueSymptomNames(symptomNames0);
        assertEquals(new ArrayList<>(), symptomNames0);
        Symptom.filterUniqueSymptomNames(symptomNames1);
        assertEquals(new ArrayList<>(Arrays.asList("symp2", "symp1", "symp3")), symptomNames1);
        Symptom.filterUniqueSymptomNames(symptomNames2);
        assertEquals(new ArrayList<>(Arrays.asList("symp2", "symp1", "symp3")), symptomNames2);
        Symptom.filterUniqueSymptomNames(symptomNames3);
        assertEquals(new ArrayList<>(Arrays.asList("symp2", "symp1", "symp3")), symptomNames3);
        Symptom.filterUniqueSymptomNames(symptomNames4);
        assertEquals(new ArrayList<>(Arrays.asList("symp2", "symp1", "symp3")), symptomNames4);
    }
}
