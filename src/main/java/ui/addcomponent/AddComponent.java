package ui.addcomponent;

import model.Study;
import persistence.StudiesSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents JSwing GUI panel with a form that adds a study to the studies database
public class AddComponent {

    public static final int HSPACE_SM = 5;
    public static final int HSPACE_MD = 10;
    public static final int HSPACE_LG = 25;
    public static final int VSPACE_SM = 5;
    public static final int VSPACE_MD = 10;
    public static final int VSPACE_LG = 25;
    public static final int TEXT_FIELD_WIDTH = 100;
    public static final int TEXT_FIELD_CHAR_WIDTH = 20;
    public static final int TEXT_FIELD_HEIGHT = 50;

    private static final Color BACKGROUND_COLOR = new Color(76, 161, 145);

    private DiseaseFormList diseaseFormList;
    private final JScrollPane jscrollPane;
    private final JPanel mainJpanel;
    private final JButton submitButt;
    private final GridBagConstraints gbcMF;


    // EFFECTS: constructs an AddComponent with a main JPanel in GridBadLayout with a ScrollPane,
    // containing diseaseFormList to input study data, and a submit button.
    public AddComponent() {
        mainJpanel = new JPanel(new GridBagLayout());
        //mainJpanel.setBackground(BACKGROUND_COLOR);
        diseaseFormList = new DiseaseFormList(1);
        submitButt = new JButton("Add Study to Data");
        submitButt.addActionListener(new SubmitActionListener());

        gbcMF = new GridBagConstraints();
        gbcMF.gridx = 0;
        gbcMF.gridy = 0;
        mainJpanel.add(diseaseFormList.getJpanel(), gbcMF);
        gbcMF.gridx = 0;
        gbcMF.gridy = 1;
        mainJpanel.add(submitButt, gbcMF);

        jscrollPane = new JScrollPane(mainJpanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jscrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    // ActionListener for when button to submit form is pressed that adds a study to studies database
    // MODIFIES: StudiesSingleton.studies
    private class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addStudyData();
        }
    }

    //EFFECTS: gets user input of study and saves in studies database.
    //         Prints error message if no numbers in number fields
    //MODIFIES: this
    public void addStudyData() {
        Study submittedStudy;
        try {
            submittedStudy = diseaseFormList.getStudyFromFieldInputs();
            StudiesSingleton.makeInstance().addStudyToSaveFile(submittedStudy);
        } catch (NumberFormatException e) {
            System.err.println("Did not enter number in a field that required a number (probably... maybe).");
        }
    }

    public JScrollPane getJscrollPane() {
        return jscrollPane;
    }
}
