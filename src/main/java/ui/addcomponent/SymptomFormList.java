package ui.addcomponent;

import model.Disease;
import model.Symptom;
import ui.muiswing.DashedBottomBorder;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents JSwing GUI panel with a part of a form to enter all the info for a Disease
public class SymptomFormList extends FormList implements Form {

    private DiseaseForm diseaseForm;

    // EFFECTS: creates a SymptomFormList
    public SymptomFormList(int formNum) {
        super(formNum);
    }

    // EFFECTS: return new SymptomForm
    protected Form makeFormToAdd() {
        return new SymptomForm(getNextFormNum());
    }

    // EFFECTS: sets diseaseForm as a new DiseaseForm and returns its main Jpanel
    // MODIFIES: this
    protected JPanel makeTopJpanel() {
        diseaseForm = new DiseaseForm(formNum);
        return diseaseForm.getJpanel();
    }

    protected JPanel makeBotJpanel(ActionListener addActionListener, ActionListener removeActionListener) {
        JPanel outerBotJpanel = new JPanel(new BorderLayout());
        //outerBotJpanel.setBackground(Color.green);
        outerBotJpanel.setBorder(new DashedBottomBorder(new ColorUIResource(84, 110, 122), 10));
        outerBotJpanel.add(new AddRemoveButtons(addActionListener, removeActionListener).getJpanel(), BorderLayout.WEST);
        return outerBotJpanel;
    }

    // EFFECTS: returns Disease from JTextField inputs of diseaseForm and subForms
    //          throws NumberFormatException when any JTextField for affected doesn't contain a number.
    public Disease getDiseaseFromFieldInputs() throws NumberFormatException {
        Disease disease = diseaseForm.getDiseaseFromFieldInput();
        disease.addAllSymptoms(getSymptomsFromFieldInputs());
        return disease;
    }

    // EFFECTS: returns symptoms from JTextField inputs of subForms
    //          throws NumberFormatException when any JTextField for affected doesn't contain a number.
    private ArrayList<Symptom> getSymptomsFromFieldInputs() throws NumberFormatException {
        ArrayList<Symptom> symptoms = new ArrayList<>();
        for (Form form : subForms) {
            symptoms.add(((SymptomForm) form).getSymptomFromFieldInput());
        }
        return symptoms;
    }
}
