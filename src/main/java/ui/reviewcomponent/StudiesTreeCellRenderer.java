package ui.reviewcomponent;

import model.Disease;
import model.Study;
import model.Symptom;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.Collection;

//Renders JTree with custom icon for when the UserObject is a Symptom, Disease, Study, or Collection
// and labels the nodes with their data
public class StudiesTreeCellRenderer implements TreeCellRenderer {
    private static ImageIcon SYMPTOM_ICON = new ImageIcon("src/main/resources/icons/clipboard-pulse-outline-24.png");
    private static ImageIcon DISEASE_ICON = new ImageIcon("src/main/resources/icons/emoticon-sick-outline-24.png");
    private static ImageIcon STUDY_ICON = new ImageIcon("src/main/resources/icons/folder-outline-24.png");
    private static ImageIcon STUDIES_ICON = new ImageIcon("src/main/resources/icons/folder-multiple-outline-24.png");
    private final JLabel jlabel;

    // EFFECTS: constructs a StudiesTreeCellRenderer with jlabel as an empty JLabel
    public StudiesTreeCellRenderer() {
        jlabel = new JLabel();
    }

    // EFFECTS: returns a JLabel with based on val and it's subtype:
    //    - Symptom: sets Icon to SYMPTOM_ICON, sets text to "P(symptomName/diseaseName) = Affected/diseaseAffected"
    //    - Disease: sets Icon to DISEASE_ICON, sets text to "P(diseaseName) = diseaseAffected/studySampleSize"
    //    - Study: sets Icon to STUDY_ICON, sets text to "Study with allDiseaseNames"
    //    - Collection: sets Icon to STUDIES_ICON, sets text to "Study Data:"
    // MODIFIES: this
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object val, boolean selected,
                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Object nodeObj = ((DefaultMutableTreeNode) val).getUserObject();
        if (nodeObj instanceof Symptom) {
            Symptom symptom = (Symptom) nodeObj;
            DefaultMutableTreeNode parentObj = (DefaultMutableTreeNode) ((DefaultMutableTreeNode) val).getParent();
            Disease parentDisease = (Disease) parentObj.getUserObject();
            jlabel.setText("P(" + symptom.getName() + " | " + parentDisease.getName() + ") = "
                    + symptom.getAffected() + "/" + parentDisease.getAffected());
            jlabel.setIcon(SYMPTOM_ICON);
        } else if (nodeObj instanceof Disease) {
            jlabel.setIcon(DISEASE_ICON);
            Disease disease = (Disease) nodeObj;
            DefaultMutableTreeNode parentObj = (DefaultMutableTreeNode) ((DefaultMutableTreeNode) val).getParent();
            Study parentStudy = (Study) parentObj.getUserObject();
            jlabel.setText("P(" + disease.getName() + ") = " + disease.getAffected()
                    + "/" + parentStudy.getSampleSize());
        } else if (nodeObj instanceof Study) {
            Study study = (Study) nodeObj;
            jlabel.setIcon(STUDY_ICON);
            jlabel.setText("Study with " + study.getAllDiseaseNames());
        } else if (nodeObj instanceof Collection) {
            Collection<Object> collection = (Collection) nodeObj;
            if (!collection.isEmpty()) {
                jlabel.setIcon(STUDIES_ICON);
                jlabel.setText("Study Data:");
            }
        }
        return jlabel;
    }
}
