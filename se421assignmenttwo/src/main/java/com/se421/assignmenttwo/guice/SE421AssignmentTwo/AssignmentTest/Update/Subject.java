package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Update;

public interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
