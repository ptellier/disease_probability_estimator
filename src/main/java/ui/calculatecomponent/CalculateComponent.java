package ui.calculatecomponent;

import model.Disease;
import model.Study;
import persistence.StudiesSingleton;
import ui.SingletonObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents JSwing GUI panel with symptom checklist, button to trigger calculations,
//            and resultant displayed probabilities of diseases
public class CalculateComponent implements SingletonObserver {
    SymptomCheckList symptomCheckList;
    JScrollPane jscrollPane;
    JPanel outerJpanel;
    JPanel leftJpanel;
    JPanel rightJpanel;
    JButton calculateButt;

    // EFFECTS: constructs a CalculateComponent with a main JPanel in a ScrollPane, inside main JPanel has
    //          - left JPanel with JCheckBoxes from symptomCheckList in BoxLayout on Y axis
    //          - right JPanel starts empty in BoxLayout on Y axis
    public CalculateComponent() {

        symptomCheckList = new SymptomCheckList(StudiesSingleton.getUniqueSymptomNames());
        leftJpanel = new JPanel();
        rightJpanel = new JPanel();
        leftJpanel.setLayout(new BoxLayout(leftJpanel, BoxLayout.Y_AXIS));
        rightJpanel.setLayout(new BoxLayout(rightJpanel, BoxLayout.Y_AXIS));

        for (JCheckBox checkBox : symptomCheckList.getJCheckBoxes()) {
            leftJpanel.add(checkBox);
        }
        leftJpanel.add(Box.createVerticalStrut(20));
        calculateButt = new JButton("Calculate Disease Probabilities");
        calculateButt.addActionListener(new CalculateActionListener());
        leftJpanel.add(calculateButt);

        outerJpanel = new JPanel(new GridBagLayout());
        outerJpanel.add(leftJpanel);
        outerJpanel.add(rightJpanel);

        jscrollPane = new JScrollPane(outerJpanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    // ActionListener for when button to calculate probability is pressed
    //        that adds JLabels about probability to rightJPanel
    // MODIFIES: this
    private class CalculateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            rightJpanel.removeAll();
            for (Study study : StudiesSingleton.makeInstance().getStudies()) {
                study.findAllProbs(symptomCheckList.getCheckedSymptomNames());
                for (Disease disease : study.getDiseases()) {
                    String diseaseProbabilityString = "P(" + disease.getName() + ") = " + disease.getProb();
                    rightJpanel.add(new JLabel(diseaseProbabilityString));
                }
            }
            rightJpanel.repaint();
            rightJpanel.revalidate();
        }
    }

    // EFFECTS: sets symptomCheckList to new SymptomCheckList made from unique symptom names in StudiesSingleton
    //          clears leftJpanel contents and adds JCheckboxes from symptomCheckList as well as calculateButt
    // MODIFIES: this
    @Override
    public void update() {
        leftJpanel.removeAll();
        symptomCheckList = new SymptomCheckList(StudiesSingleton.getUniqueSymptomNames());
        for (JCheckBox checkBox : symptomCheckList.getJCheckBoxes()) {
            leftJpanel.add(checkBox);
        }
        leftJpanel.add(calculateButt);
    }

    public JScrollPane getJscrollPane() {
        return jscrollPane;
    }
}
