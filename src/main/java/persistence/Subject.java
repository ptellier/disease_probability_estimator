package persistence;

import ui.SingletonObserver;

import java.util.ArrayList;

// represents a subject of observation which notifies observer objects after important events
public class Subject {
    private ArrayList<SingletonObserver> observers = new ArrayList<>();

    // EFFECTS: adds an observer to observers
    // MODIFIES: this
    public void addObserver(SingletonObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    // EFFECTS: makes all observers update
    public void notifyObservers() {
        for (SingletonObserver observer : observers) {
            observer.update();
        }
    }

    public ArrayList<SingletonObserver> getObservers() {
        return observers;
    }
}
