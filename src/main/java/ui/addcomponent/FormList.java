package ui.addcomponent;

import ui.muiswing.DashedBottomBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// represents a JSwing GUI component with an adjustable top, a middle, and a bottom panel.
// - middle panel has a list of Form
// - bottom panel has AddRemoveButtons to adjust number of Forms in list
abstract class FormList {

    private static final int HSPACE_SM = AddComponent.HSPACE_SM;
    private static final int HSPACE_MD = AddComponent.HSPACE_MD;
    private static final int HSPACE_LG = AddComponent.HSPACE_LG;
    private static final int VSPACE_SM = AddComponent.VSPACE_SM;
    private static final int VSPACE_MD = AddComponent.VSPACE_MD;
    private static final int VSPACE_LG = AddComponent.VSPACE_LG;
    private static final int TEXT_FIELD_WIDTH = AddComponent.TEXT_FIELD_WIDTH;
    private static final int TEXT_FIELD_CHAR_WIDTH = AddComponent.TEXT_FIELD_CHAR_WIDTH;
    private static final int TEXT_FIELD_HEIGHT = AddComponent.TEXT_FIELD_HEIGHT;

    private static final Insets TOP_INSETS = new Insets(VSPACE_SM, HSPACE_SM, VSPACE_SM, HSPACE_SM);
    private static final Insets MID_INSETS = new Insets(VSPACE_SM, HSPACE_SM, VSPACE_SM, HSPACE_SM);
    protected static final Insets BOT_INSETS = new Insets(VSPACE_SM, HSPACE_SM, VSPACE_SM, HSPACE_SM);

    private JPanel mainJpanel;
    private JPanel topJpanel;
    private JPanel midJpanel;
    private JPanel botJpanel;
    protected List<JPanel> subFormPanels;
    protected List<Form> subForms;
    private GridBagConstraints gbc;
    private AddRemoveButtons addRemoveButts;
    protected int formNum;

    // EFFECTS: constructs a FormList with GridBagLayout main JPanel.
    // - top panel is made from makeTopJpanel()
    // - mid panel has subForms list and start with one subForm
    // - bottom panel has addRemoveButtons that change number of subForms
    FormList(int formNum) {
        this.formNum = formNum;

        this.topJpanel = makeTopJpanel();
        this.botJpanel = makeBotJpanel(new AddSymptomButtonListener(), new RemoveSymptomButtonListener());

        this.midJpanel = new JPanel();
        this.midJpanel.setLayout(new BoxLayout(midJpanel, BoxLayout.Y_AXIS));

        this.mainJpanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = TOP_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel outerTopJpanel = new JPanel(new BorderLayout());
        if (setBorderSeparators() != null ) {
            outerTopJpanel.setBorder(new DashedBottomBorder(new ColorUIResource(84, 110, 122), 10));
        }
        outerTopJpanel.add(topJpanel, BorderLayout.WEST);
        mainJpanel.add(outerTopJpanel, gbc);
        gbc.insets = MID_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel outerMidJpanel = new JPanel(new BorderLayout());
        outerMidJpanel.add(midJpanel, BorderLayout.WEST);
        mainJpanel.add(outerMidJpanel, gbc);
        gbc.insets = BOT_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainJpanel.add(botJpanel, gbc);

        subForms = new ArrayList<>();
        subFormPanels = new ArrayList<>();
        addToList();

    }

    // ActionListener that adds one subForm to subForms list
    private class AddSymptomButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addToList();
        }
    }

    // ActionListener that removes one subForm to subForms list (unless only on subForm left)
    private class RemoveSymptomButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeFromList();
        }
    }

    // EFFECTS: adds a new Form from makeFormToAdd() to subForms and its JPanel to subFormPanels. repaints main JPanel
    // MODIFIES: this
    private void addToList() {
        Form newForm = makeFormToAdd();
        subForms.add(newForm);
        JPanel newFormJpanel;
        newFormJpanel = newForm.getJpanel();
        midJpanel.add(newFormJpanel);
        subFormPanels.add(newFormJpanel);

        mainJpanel.repaint();
        mainJpanel.revalidate();
    }

    // EFFECTS: removes a new Form from subForms and removes its JPanel to subFormPanels. repaints main JPanel
    //          does nothing if subFormPanels has one or less JPanel
    // MODIFIES: this
    private void removeFromList() {
        if (subFormPanels.size() > 1) {
            JPanel removedJpanel = subFormPanels.remove(subFormPanels.size() - 1);
            subForms.remove(subForms.size() - 1);
            midJpanel.remove(removedJpanel);

            mainJpanel.repaint();
            mainJpanel.revalidate();
        }
    }

    // EFFECTS: returns null in place of the border to be used for outerTopJpanel and outerMidJpanel
    protected Border setBorderSeparators() {
        return null;
    }



    protected abstract Form makeFormToAdd();

    protected int getNextFormNum() {
        return subForms.size() + 1;
    }

    protected abstract JPanel makeTopJpanel();

    protected abstract JPanel makeBotJpanel(ActionListener addActionListener, ActionListener removeActionListener);

    public JPanel getJpanel() {
        return mainJpanel;
    }
}
