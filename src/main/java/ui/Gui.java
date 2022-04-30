package ui;

import model.Event;
import model.EventLog;
import persistence.StudiesSingleton;
import ui.addcomponent.AddComponent;
import ui.calculatecomponent.CalculateComponent;
import ui.deletecomponent.DeleteComponent;
import ui.reviewcomponent.ReviewComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// The main JavaSwing GUI
public class Gui {

    private static final int MIN_WINDOW_WIDTH = 800;
    private static final int MIN_WINDOW_HEIGHT = 600;

    JFrame mainWindow;
    private static final String programIconPath = "src/main/resources/icons/folio-icon.png";

    private static ImageIcon calculateIcon = new ImageIcon("src/main/resources/icons/calculator-24.png");
    private static final String calculateTabTip = "Calculate disease probabilities from symptoms";
    private static ImageIcon reviewIcon = new ImageIcon("src/main/resources/icons/text-box-search-outline-24.png");
    private static final String reviewTabTip = "See the studies currently in the database";
    private static ImageIcon addIcon = new ImageIcon("src/main/resources/icons/plus-circle-outline-24.png");
    private static final String addTabTip = "Add data from a new study to the database of studies";
    private static ImageIcon deleteIcon = new ImageIcon("src/main/resources/icons/trash-can-outline-24.png");
    private static final String deleteTabTip = "Delete the data for a study from the database of studies";

    CalculateComponent calculateComp;
    ReviewComponent reviewComp;
    AddComponent addComp;
    DeleteComponent deleteComp;

    JTabbedPane navTabs;

    // EFFECTS: constructs a GUI with main JFrame with four new JComponents:
    //          CalculateComponent, ReviewComponent, AddComponent, DeleteComponent.
    //          Puts them in 4 JTabbedPane tabs
    public Gui() {
        calculateComp = new CalculateComponent();
        reviewComp = new ReviewComponent();
        addComp = new AddComponent();
        deleteComp = new DeleteComponent();

        navTabs = new JTabbedPane();
        navTabs.addTab("Check Disease Probability", calculateIcon, calculateComp.getJscrollPane(), calculateTabTip);
        navTabs.addTab("Current Data", reviewIcon, reviewComp.getJscrollPane(), reviewTabTip);
        navTabs.addTab("Add Study", addIcon, addComp.getJscrollPane(), addTabTip);
        navTabs.addTab("Delete Study", deleteIcon, deleteComp.getJscrollPane(), deleteTabTip);
        navTabs.setMnemonicAt(0, KeyEvent.VK_1);
        navTabs.setMnemonicAt(1, KeyEvent.VK_2);
        navTabs.setMnemonicAt(2, KeyEvent.VK_3);
        navTabs.setMnemonicAt(3, KeyEvent.VK_4);

        mainWindow = new JFrame();
        try {
            mainWindow.setIconImage(ImageIO.read(new File(programIconPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainWindow.addWindowListener(new LoggingWindowAdapter());

        mainWindow.add(navTabs);
        mainWindow.setSize(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT);

        for (SingletonObserver observer : Arrays.asList(calculateComp, reviewComp, deleteComp)) {
            StudiesSingleton.makeInstance().addObserver(observer);
        }

        mainWindow.setVisible(true);
    }

    //Represents a WindowAdapter that causes Window to print all Events in EventLog to console before quitting
    private class LoggingWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent wevent) {
            for (Event levent : EventLog.getInstance()) {
                System.out.println(levent.toString());
            }
        }
    }
}
