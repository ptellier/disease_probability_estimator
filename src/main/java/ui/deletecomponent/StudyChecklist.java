package ui.deletecomponent;

import model.Study;

import javax.swing.*;
import java.util.*;

// Represents JSwing GUI checkboxes with the names of Studies in a database
public class StudyChecklist {

    Map<JCheckBox, Study> checkBoxesMap;

    // EFFECTS: constructs a StudyChecklist with checkBoxesMap set to HashMap of JCheckBoxes mapped to all Studies
    public StudyChecklist(Collection<Study> studies) {
        checkBoxesMap = new HashMap<>();
        for (Study study : studies) {
            checkBoxesMap.put(makeCheckBox(study), study);
        }
    }

    // EFFECTS: returns a new JCheckBox labelled with names of diseases in study
    private JCheckBox makeCheckBox(Study study) {
        StringBuilder stringBuilder = new StringBuilder("Study with");
        for (String diseaseName: study.getAllDiseaseNames()) {
            stringBuilder.append(" " + diseaseName);
        }
        return new JCheckBox(stringBuilder.toString());
    }

    // EFFECTS: returns all the Studies of checked JCheckBoxes in checkBoxesMap
    public ArrayList<Study> getCheckedStudies() {
        ArrayList<Study> studies = new ArrayList<>();
        for (Map.Entry<JCheckBox, Study> entry : checkBoxesMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                studies.add(entry.getValue());
            }
        }
        return studies;
    }

    public Set<JCheckBox> getJCheckBoxes() {
        return checkBoxesMap.keySet();
    }
}
