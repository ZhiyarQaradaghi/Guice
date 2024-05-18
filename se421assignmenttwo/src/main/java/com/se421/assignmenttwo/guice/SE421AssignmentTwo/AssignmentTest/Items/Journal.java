package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items;

public class Journal implements LibraryItem {
    private String ID;
    private String title;
    private String description;

    public Journal(String ID, String title, String description) {
        this.ID = ID;
        this.title = title;
        this.description = description;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }
}