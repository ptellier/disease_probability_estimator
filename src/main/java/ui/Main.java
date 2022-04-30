package ui;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialOceanicTheme;
import persistence.StudiesSingleton;
import ui.muiswing.CustomMaterialLiteTheme;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;

//Initializes and runs the probability application
public class Main {

    private static final String filePath = "data/studyData.json";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new CustomMaterialLiteTheme()));
            //UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
            //UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));

            IconUIResource emptyIcon = new IconUIResource(new Icon() {
                @Override public void paintIcon(Component c, Graphics g, int x, int y) {}
                @Override public int getIconWidth() {
                    return 0;
                }
                @Override public int getIconHeight() {
                    return 0;
                }
            });

            UIManager.put("Tree.expandedIcon",  emptyIcon);
            UIManager.put("Tree.collapsedIcon", emptyIcon);
            //UIManager.put("Tree.closedIcon",    emptyIcon);
            //UIManager.put("Tree.openIcon",      emptyIcon);
            UIManager.put("Tree.paintLines",    Boolean.TRUE);
            UIManager.put("Tree.drawHorizontalLines",    Boolean.TRUE);
            UIManager.put("Tree.drawVerticalLines",    Boolean.FALSE);

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        StudiesSingleton.makeInstance().switchFiles(filePath);
        Gui gui = new Gui();
    }
}
