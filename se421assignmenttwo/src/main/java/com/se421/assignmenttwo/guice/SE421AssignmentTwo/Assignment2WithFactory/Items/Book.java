package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items;

public class Book implements LibraryItem {
    private final String ID;
    private final String title;
    private final String description;

    public Book() {
        this.ID = this.title = this.description = "";
    }

    public Book(String id, String title, String description) {
        this.ID = id;
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

    @Override
    public String getType() {
        return "Book";
    }
}