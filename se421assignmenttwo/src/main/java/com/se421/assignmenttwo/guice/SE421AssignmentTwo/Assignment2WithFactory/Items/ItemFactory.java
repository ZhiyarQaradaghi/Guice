package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items;

public class ItemFactory {
    public static LibraryItem createItem(LibraryItem item) {
        switch (item.getType().toLowerCase()) {
            case "book":
                return new ItemBuilder(item.getID()).setTitle(item.getTitle()).setDescription(item.getDescription())
                        .buildBook();
            case "journal":
                return new ItemBuilder(item.getID()).setTitle(item.getTitle()).setDescription(item.getDescription())
                        .buildJournal();
            case "technicalpaper":
                return new ItemBuilder(item.getID()).setTitle(item.getTitle()).setDescription(item.getDescription())
                        .buildTechnicalPaper();
            case "video":
                return new ItemBuilder(item.getID()).setTitle(item.getTitle()).setDescription(item.getDescription())
                        .buildVideo();
            default:
                throw new IllegalArgumentException("Invalid item type: " + item.getType());
        }
    }
}
