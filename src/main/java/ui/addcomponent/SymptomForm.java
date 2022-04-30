package ui.addcomponent;

import model.Symptom;

import java.awt.*;

// Represents JSwing GUI panel with a part of a form to enter the name of a symptom and number of people affected
public class SymptomForm extends TwoFieldForm {

    private static final Color BACKGROUND_COLOR = new Color(123, 76, 161, 37);

    // EFFECTS: create a SymptomForm as a TwoFieldForm with the labels "Disease Name", "Number Affected"
    public SymptomForm(int formNum) {
        super("Name", "Number Affected", "Symptom #" + formNum + ":", FormType.LEFT_LABEL);
        //getJpanel().setBackground(BACKGROUND_COLOR);
    }

    // EFFECTS: returns a symptom with symptomName and affected from JTextField inputs of TwoFieldForm.
    //          throws NumberFormatException when JTextField for affected doesn't contain a number.
    public Symptom getSymptomFromFieldInput() throws NumberFormatException {
        String symptomName = firstField.getText();
        int affected = Integer.parseInt(secondField.getText());
        return new Symptom(symptomName, affected);
    }
}
