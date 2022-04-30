package ui.addcomponent;

import model.Disease;

import java.awt.*;

// Represents JSwing GUI panel with a part of a form to enter the name of a disease
//         and number of people in the study affected
public class DiseaseForm extends TwoFieldForm {

    private static final Color BACKGROUND_COLOR = new Color(87, 161, 76, 39);

    // EFFECTS: create a DiseaseForm as a TwoFieldForm with the labels "Disease Name", "Number Affected"
    public DiseaseForm(int formNum) {
        super("Name", "Number Affected", "Disease #" + formNum + ":", FormType.OVER_TOP_LABEL);
        //getJpanel().setBackground(BACKGROUND_COLOR);
    }

    // EFFECTS: returns a disease with diseaseName and affected from JTextField inputs of TwoFieldForm (no symptoms yet)
    //          throws NumberFormatException when JTextField for affected doesn't contain a number.
    public Disease getDiseaseFromFieldInput() throws NumberFormatException {
        String diseaseName = firstField.getText();
        int affected = Integer.parseInt(secondField.getText());
        return new Disease(diseaseName, affected);
    }

}
