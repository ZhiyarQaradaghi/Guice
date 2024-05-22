package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.System;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine.FineCalculator;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine.FineAnnotations.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Items.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Lender.LendRecord;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Listener.LibrarySystemListener;

@Singleton
public class LibrarySystem {
    private List<LibraryItem> allItems;
    private Map<String, List<LendRecord>> borrowedItems;
    private FineCalculator bookFineCalculator;
    private FineCalculator journalFineCalculator;
    private FineCalculator technicalPaperFineCalculator;
    private FineCalculator videoFineCalculator;
    private List<LibrarySystemListener> listeners; // use list for listeners instead of array

    @Inject
    public LibrarySystem(@BookFineAnno FineCalculator bookFineCalculator,
            @JournalFineAnno FineCalculator journalFineCalculator,
            @TechnicalPaperFineAnno FineCalculator technicalPaperFineCalculator,
            @VideoFineAnno FineCalculator videoFineCalculator) {

        this.allItems = new ArrayList<>();
        this.borrowedItems = new HashMap<>();
        this.bookFineCalculator = bookFineCalculator;
        this.journalFineCalculator = journalFineCalculator;
        this.technicalPaperFineCalculator = technicalPaperFineCalculator;
        this.videoFineCalculator = videoFineCalculator;
        this.listeners = new ArrayList<>(); // so that we dont get null pointer exception
    }

    public boolean borrowItem(String auisID, LibraryItem item, Date lendDate, Date dueDate, Date returnDate) {
        if (countBorrowedItems(auisID) >= 5) {
            System.out.println("You have reached the maximum borrow limit. Return some items to borrow more.");
            return false;
        }

        LendRecord lendRecord = new LendRecord(bookFineCalculator, journalFineCalculator,
                technicalPaperFineCalculator, videoFineCalculator, item, lendDate, dueDate);
        lendRecord.setReturnDate(returnDate);
        borrowedItems.computeIfAbsent(auisID, k -> new ArrayList<>()).add(lendRecord);

        for (LibrarySystemListener listener : listeners) {
            listener.onItemBorrowed(auisID, item, lendDate, dueDate, returnDate);
        }
        System.out.println("Notification: Library system has been updated.");
        return true;
    }

    public List<LendRecord> getBorrowedItems(String auisID) {
        return borrowedItems.getOrDefault(auisID, new ArrayList<>());
    }

    public LibraryItem getLibraryItem(String libraryID) {
        for (LibraryItem item : allItems) {
            if (item.getID().equals(libraryID)) {
                return item;
            }
        }
        return null;
    }

    public boolean addItem(LibraryItem item) {
        return allItems.add(item);
    }

    private int countBorrowedItems(String auisID) {
        return borrowedItems.getOrDefault(auisID, new ArrayList<>()).size();
    }

    // listener design pattern !!!

    public void addListener(LibrarySystemListener listener) {
        listeners.add(listener);
    }

}
