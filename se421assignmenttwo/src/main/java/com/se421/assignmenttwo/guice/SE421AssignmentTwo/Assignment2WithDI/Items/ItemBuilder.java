package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Items;

public class ItemBuilder {
    private String ID;
    private String title;
    private String description;

    public ItemBuilder(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public ItemBuilder setID(String ID) {
        this.ID = ID;
        return this;
    }

    public ItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Book buildBook() {
        Book book = new Book(this.getID(), this.getTitle(), this.getDescription());
        return book;
    }

    public Journal buildJournal() {
        return new Journal(this.getID(), this.getTitle(), this.getDescription());
    }

    public TechnicalPaper buildTechnicalPaper() {
        return new TechnicalPaper(this.getID(), this.getTitle(), this.getDescription());
    }

    public Video buildVideo() {
        return new Video(this.getID(), this.getTitle(), this.getDescription());
    }
}