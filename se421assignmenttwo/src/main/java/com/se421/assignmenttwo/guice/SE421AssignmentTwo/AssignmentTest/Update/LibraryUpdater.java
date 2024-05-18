package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Update;

public class LibraryUpdater implements Observer {
    @Override
    public void update() {
        System.out.println("Notification: Library system has been updated.");
    }
}