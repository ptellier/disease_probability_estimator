package ui.deletecomponent;

import persistence.StudiesSingleton;
import ui.SingletonObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents JSwing GUI panel with checklist of studies and button to trigger deleting checked studies
public class DeleteComponent implements SingletonObserver {

    StudyChecklist studyChecklist;
    JScrollPane jscrollPane;
    JPanel outerJpanel;
    JPanel jpanel;
    JButton deleteButt;

    // EFFECTS: constructs a DeleteComponent with a main outer JPanel in a ScrollPane. Sets inner JPanel in BoxLayout
    //          and adds StudyChecklist JPanels, and deleteButt to delete selected.
    public DeleteComponent() {
        studyChecklist = new StudyChecklist(StudiesSingleton.makeInstance().getStudies());
        jpanel = new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));

        for (JCheckBox checkBox : studyChecklist.getJCheckBoxes()) {
            jpanel.add(checkBox);
        }
        deleteButt = new JButton("Delete");
        deleteButt.addActionListener(new DeleteActionListener());

        jpanel.add(Box.createVerticalStrut(20));
        jpanel.add(deleteButt);

        outerJpanel = new JPanel(new GridBagLayout());
        outerJpanel.add(jpanel);

        jscrollPane = new JScrollPane(outerJpanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    // ActionListener for when button to delete some studies is pressed that removes studies from StudiesSingleton data
    // MODIFIES: this
    private class DeleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StudiesSingleton.makeInstance().removeAll(studyChecklist.getCheckedStudies());
        }
    }

    // EFFECTS: clears JPanel contents and adds JCheckboxes from new studiesCheckList made from studies in Singleton
    //          as well as deleteButt
    // MODIFIES: this
    @Override
    public void update() {
        jpanel.removeAll();
        studyChecklist = new StudyChecklist(StudiesSingleton.makeInstance().getStudies());
        for (JCheckBox checkBox : studyChecklist.getJCheckBoxes()) {
            jpanel.add(checkBox);
        }
        jpanel.add(deleteButt);
    }

    public JScrollPane getJscrollPane() {
        return jscrollPane;
    }
}
