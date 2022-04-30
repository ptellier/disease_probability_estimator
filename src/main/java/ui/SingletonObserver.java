package ui;

// Represents an observer of StudiesSingleton that can be updated when relevant changes happen in the Subject database
public interface SingletonObserver {
    void update();
}
