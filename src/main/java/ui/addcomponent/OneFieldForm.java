package ui.addcomponent;

import javax.swing.*;
import java.awt.*;

// Represents JSwing GUI panel with a form part consisting of a JLabel above a JTextField
public class OneFieldForm {

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

    JPanel oneFieldJPanel;
    JLabel firstLabel;
    JTextField firstField;

    private static final Color BACKGROUND_COLOR = new Color(76, 161, 141, 37);

    // EFFECTS: constructs a OneFieldForm with a main JPanel in GridBadLayout, a JTextField, and JLabel of firstName
    public OneFieldForm(String firstName) {
        oneFieldJPanel = new JPanel(new GridBagLayout());
        //oneFieldJPanel.setBackground(BACKGROUND_COLOR);
        firstLabel = new JLabel(firstName);
        firstField = new JTextField(TEXT_FIELD_CHAR_WIDTH);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;

        c.insets = LABEL_INSETS;
        c.gridx = 0;
        c.gridy = 0;
        oneFieldJPanel.add(firstLabel, c);

        c.insets = FIELD_INSETS;
        c.gridx = 0;
        c.gridy = 1;
        oneFieldJPanel.add(firstField, c);
    }

    public JPanel getJpanel() {
        return oneFieldJPanel;
    }

    public JLabel getFirstLabel() {
        return firstLabel;
    }

    public JTextField getFirstField() {
        return firstField;
    }
}