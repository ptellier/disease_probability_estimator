package ui.reviewcomponent;

import model.Disease;
import model.Study;
import model.Symptom;
import persistence.StudiesSingleton;
import ui.SingletonObserver;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;

// Represents JSwing GUI panel with JTree displaying all the study data in the database
public class ReviewComponent implements SingletonObserver {

    private JTree studiesTree;
    DefaultMutableTreeNode root;
    TreeCellRenderer renderer;

    JScrollPane jscrollPane;
    JPanel jpanel;

    // EFFECTS: constructs a ReviewComponent with a main JPanel in a ScrollPane.
    //    Sets renderer to new StudiesTreeRenderer. creates JTree from StudiesSingleton with renderer and
    //    assigns to studiesTree. Adds studiesTree to main jpanel.
    public ReviewComponent() {

        renderer = new StudiesTreeCellRenderer();

        studiesTree = createJTree();

        jpanel = new JPanel(new GridBagLayout());
        jpanel.add(studiesTree);

        jscrollPane = new JScrollPane(jpanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    // EFFECTS: Removes everything from main JPanel and adds new JComponent from createJTree()
    // MODIFIES: this
    @Override
    public void update() {
        jpanel.removeAll();
        jpanel.add(createJTree());

    }

    // EFFECTS: returns a JTree with CellRenderer set as renderer. JTree displays all the studies in StudiesSingleton.
    // MODIFIES: this
    private JTree createJTree() {
        ArrayList<Study> studies = StudiesSingleton.makeInstance().getStudies();
        root = new DefaultMutableTreeNode(studies);
        DefaultMutableTreeNode nextStudyNode;
        for (Study study : studies) {
            nextStudyNode = new DefaultMutableTreeNode(study);
            createStudyNode(nextStudyNode, study);
            root.add(nextStudyNode);
        }
        studiesTree = new JTree(root);
//        if (renderer != null) {
//            studiesTree.setCellRenderer(renderer);
//        }
        return studiesTree;
    }

    // EFFECTS: adds all the data about symptoms and diseases from study to studyNode
    // MODIFIES: studyNode
    private void createStudyNode(DefaultMutableTreeNode studyNode, Study study) {
        assert study != null : "study passed to createStudyNode should not be null";
        DefaultMutableTreeNode nextDiseaseNode;
        for (Disease disease : study.getDiseases()) {
            nextDiseaseNode = new DefaultMutableTreeNode(disease);
            createDiseaseNode(nextDiseaseNode, disease);
            studyNode.add(nextDiseaseNode);
        }
    }

    // EFFECTS: adds all the data about symptoms from disease to diseaseNode
    // MODIFIES: diseaseNode
    private void createDiseaseNode(DefaultMutableTreeNode diseaseNode, Disease disease) {
        assert disease != null : "disease passed to createDiseaseNode should not be null";
        DefaultMutableTreeNode nextSymptomNode;
        for (Symptom symptom : disease.getSymptoms()) {
            nextSymptomNode = new DefaultMutableTreeNode(symptom);
            diseaseNode.add(nextSymptomNode);
        }
    }

    public JScrollPane getJscrollPane() {
        return jscrollPane;
    }
}
