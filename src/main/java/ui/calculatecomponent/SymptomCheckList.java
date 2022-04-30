package ui.calculatecomponent;

import javax.swing.*;
import java.util.*;

// Represents JSwing GUI checkboxes with the names of symptoms of a disease
public class SymptomCheckList {

    Map<JCheckBox, String> checkBoxesMap;

    // EFFECTS: constructs a SymptomCheckList with checkBoxesMap set to HashMap of JCheckBoxes mapped to symptom names
    public SymptomCheckList(Collection<String> symptomsNames) {
        checkBoxesMap = new HashMap<>();
        for (String symptomName : symptomsNames) {
            checkBoxesMap.put(makeCheckBox(symptomName), symptomName);
        }
    }

    // EFFECTS: returns a new JCheckBox labelled with symptomName
    private JCheckBox makeCheckBox(String symptomName) {
        assert symptomName != null : "symptomName cannot be null";
        return new JCheckBox(symptomName);
    }

    // EFFECTS: returns all the symptom names of checked JCheckBoxes in checkBoxesMap
    public ArrayList<String> getCheckedSymptomNames() {
        ArrayList<String> symptomNames = new ArrayList<>();
        for (Map.Entry<JCheckBox, String> entry : checkBoxesMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                symptomNames.add(entry.getValue());
            }
        }
        return symptomNames;
    }

    public Set<JCheckBox> getJCheckBoxes() {
        return checkBoxesMap.keySet();
    }
}
