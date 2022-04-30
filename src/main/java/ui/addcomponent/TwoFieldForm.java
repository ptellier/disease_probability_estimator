package ui.addcomponent;

import javax.swing.*;
import java.awt.*;

// Represents JSwing GUI panel with a form part consisting of a two JLabels above a two JTextFields
public class TwoFieldForm implements Form {

    private static final int HSPACE_SM = AddComponent.HSPACE_SM;
    private static final int HSPACE_MD = AddComponent.HSPACE_MD;
    private static final int HSPACE_LG = AddComponent.HSPACE_LG;
    private static final int VSPACE_SM = AddComponent.VSPACE_SM;
    private static final int VSPACE_MD = AddComponent.VSPACE_MD;
    private static final int VSPACE_LG = AddComponent.VSPACE_LG;
    private static final int TEXT_FIELD_WIDTH = AddComponent.TEXT_FIELD_WIDTH;
    private static final int TEXT_FIELD_CHAR_WIDTH = AddComponent.TEXT_FIELD_CHAR_WIDTH;
    private static final int TEXT_FIELD_HEIGHT = AddComponent.TEXT_FIELD_HEIGHT;

    private static final Insets LABEL_INSETS = new Insets(VSPACE_SM, HSPACE_SM, 0, HSPACE_SM);
    private static final Insets FIELD_INSETS = new Insets(0, HSPACE_SM, VSPACE_SM, HSPACE_SM);
    private static final Insets LEFT_SIDE_INSETS = new Insets(VSPACE_MD, 0, 0, HSPACE_MD);



    protected JPanel twoFieldJPanel;
    protected JLabel firstLabel;
    protected JTextField firstField;
    protected JLabel secondLabel;
    protected JTextField secondField;
    protected JLabel extraJlabel;
    private GridBagConstraints gbc;

    // EFFECTS: constructs a TwoFieldForm with a main JPanel in GridBadLayout,
    // and with two JLabels of firstName and secondName above two JTextFields
    public TwoFieldForm(String firstName, String secondName) {
        initFields(firstName, secondName);
        makeSimple();
    }

    public TwoFieldForm(String firstName, String secondName, String extraName, FormType formType) {
        initFields(firstName, secondName);
        switch (formType) {
            case OVER_TOP_LABEL :
                makeOverTopLabel(extraName);
                break;
            default :
                makeLeftSideLabel(extraName);
                break;
        }
    }

    private void initFields(String firstName, String secondName) {

        twoFieldJPanel = new JPanel(new GridBagLayout());
        firstLabel = new JLabel(firstName);
        firstField = new JTextField(TEXT_FIELD_CHAR_WIDTH);

        secondLabel = new JLabel(secondName);
        secondField = new JTextField(TEXT_FIELD_CHAR_WIDTH);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
    }

    // EFFECTS: sets the TwoFieldForm with two JLabels of firstName and secondName above two JTextFields
    private void makeSimple() {
        gbc.insets = LABEL_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 0;
        twoFieldJPanel.add(firstLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        twoFieldJPanel.add(secondLabel, gbc);

        gbc.insets = FIELD_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 1;
        twoFieldJPanel.add(firstField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        twoFieldJPanel.add(secondField, gbc);
    }

    private void makeOverTopLabel(String overTopName) {

        extraJlabel = new JLabel(overTopName);
        extraJlabel.setForeground(Color.BLACK);
        Font prevFont = extraJlabel.getFont();
        extraJlabel.setFont(new Font(prevFont.getFontName(), Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        twoFieldJPanel.add(extraJlabel, gbc);

        gbc.insets = LABEL_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 1;
        twoFieldJPanel.add(firstLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        twoFieldJPanel.add(secondLabel, gbc);

        gbc.insets = FIELD_INSETS;
        gbc.gridx = 0;
        gbc.gridy = 2;
        twoFieldJPanel.add(firstField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        twoFieldJPanel.add(secondField, gbc);
    }

    private void makeLeftSideLabel(String leftSideName) {
        gbc.insets = LEFT_SIDE_INSETS;
        extraJlabel = new JLabel(leftSideName);
        extraJlabel.setForeground(Color.BLACK);
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        twoFieldJPanel.add(extraJlabel, gbc);

        gbc.insets = LABEL_INSETS;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        twoFieldJPanel.add(firstLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        twoFieldJPanel.add(secondLabel, gbc);

        gbc.insets = FIELD_INSETS;
        gbc.gridx = 1;
        gbc.gridy = 1;
        twoFieldJPanel.add(firstField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        twoFieldJPanel.add(secondField, gbc);
    }



    public JPanel getJpanel() {
        return twoFieldJPanel;
    }

    public JLabel getFirstLabel() {
        return firstLabel;
    }

    public JTextField getFirstField() {
        return firstField;
    }

    public JLabel getSecondLabel() {
        return secondLabel;
    }

    public JTextField getSecondField() {
        return secondField;
    }
}
