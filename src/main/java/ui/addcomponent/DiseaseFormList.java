package ui.addcomponent;

import model.Disease;
import model.Study;
import ui.muiswing.DashedBottomBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents JSwing GUI panel with a form to enter all the info for a study
public class DiseaseFormList extends FormList {

    private OneFieldForm studyForm;

    // EFFECTS: creates a DiseaseFormList
    public DiseaseFormList(int formNum) {
        super(formNum);
    }

    // EFFECTS: return new SymptomFormList
    @Override
    protected Form makeFormToAdd() {
        return new SymptomFormList(this.getNextFormNum());
    }

    // EFFECTS: sets studyForm as OneFieldForm with the label "Study Sample Size" and returns the JPanel
    // MODIFIES: this
    @Override
    protected JPanel makeTopJpanel() {
        JPanel outerTopJpanel = new JPanel(new BorderLayout());
        studyForm = new OneFieldForm("Study Sample Size");
        outerTopJpanel.add(studyForm.getJpanel(), BorderLayout.WEST);
        return outerTopJpanel;
    }

    // EFFECTS: returns a new set of add and remove buttons created with respective action listeners that were passed in
    @Override
    protected JPanel makeBotJpanel(ActionListener addActionListener, ActionListener removeActionListener) {
        JPanel outerBotJpanel = new JPanel(new BorderLayout());
        outerBotJpanel.add(new AddRemoveButtons(addActionListener, removeActionListener).getJpanel(), BorderLayout.WEST);
        return outerBotJpanel;
    }

    @Override
    protected Border setBorderSeparators() {
        return new DashedBottomBorder(new ColorUIResource(84, 110, 122), 10);
    }

    // EFFECTS: returns a new Study with the info in JTextFields of studyForm and symptomFormLists
    public Study getStudyFromFieldInputs() throws NumberFormatException  {
        return new Study(getSampleSizeFromFieldInput(), getDiseasesFromFieldInputs());
    }

    // EFFECTS: returns sample size from JTextField input of studyForm
    //          throws NumberFormatException when JTextField doesn't contain a number.
    private int getSampleSizeFromFieldInput() throws NumberFormatException {
        return Integer.parseInt(studyForm.getFirstField().getText());
    }

    // EFFECTS: returns diseases from JTextField inputs of subForms
    //          throws NumberFormatException when any JTextField for affected doesn't contain a number.
    private ArrayList<Disease> getDiseasesFromFieldInputs() throws NumberFormatException {
        ArrayList<Disease> diseases = new ArrayList<>();
        for (Form form : subForms) {
            diseases.add(((SymptomFormList) form).getDiseaseFromFieldInputs());

        }
        return diseases;
    }
}