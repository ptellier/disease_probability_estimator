package ui.addcomponent;

import ui.muiswing.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents JSwing GUI panel with two buttons used to add and remove elements from a list
public class AddRemoveButtons {
    JPanel jpanel;
    JButton addButt;
    JButton removeButt;

    private static ImageIcon ADD_ICON = new ImageIcon("src/main/resources/icons/plus-circle-outline-gray-24.png");
    private static ImageIcon REMOVE_ICON = new ImageIcon("src/main/resources/icons/minus-circle-outline-gray-24.png");

    // EFFECTS: constructs AddRemoveButtons with two ActionListeners and put in a JPanel in GridLayout
    public AddRemoveButtons(ActionListener addListener, ActionListener removeListener) {
        jpanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        addButt = new JButton(ADD_ICON);
        //addButt.setBorder(new RoundedBorder(30));
        addButt.setPreferredSize(new Dimension(40, 40));
        addButt.addActionListener(addListener);

        removeButt = new JButton(REMOVE_ICON);
        //removeButt.setBorder(new RoundedBorder(30));
        removeButt.setPreferredSize(new Dimension(40, 40));
        removeButt.addActionListener(removeListener);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,0,0,0);
        jpanel.add(addButt, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,10,0,0);
        jpanel.add(removeButt, gbc);
    }

    public JPanel getJpanel() {
        return jpanel;
    }
}
