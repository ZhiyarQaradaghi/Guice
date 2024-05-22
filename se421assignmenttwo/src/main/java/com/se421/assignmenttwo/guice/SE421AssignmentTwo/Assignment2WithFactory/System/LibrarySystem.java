package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Lender.LendRecord;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Listener.LibrarySystemListener;

@Singleton
public class LibrarySystem {
    private List<LibraryItem> allItems;
    private Map<String, List<LendRecord>> borrowedItems;
    private List<LibrarySystemListener> listeners; // use array list for listeners instead of array
    private static LibrarySystem instance;

    private LibrarySystem() {
        allItems = new ArrayList<>();
        borrowedItems = new HashMap<>();
        this.listeners = new ArrayList<>();
    }

    public static synchronized LibrarySystem getInstance() {
        if (instance == null) {
            instance = new LibrarySystem();
        }
        return instance;
    }

    public boolean borrowItem(String auisID, LibraryItem item, java.util.Date lendDate, java.util.Date dueDate,
            java.util.Date returnDate) {
        if (countBorrowedItems(auisID) >= 5) {
            System.out.println("You have reached the maximum borrow limit. Return some items to borrow more.");
            return false;
        }
        LendRecord lendRecord = new LendRecord(auisID, item, lendDate, dueDate);
        lendRecord.setReturnDate(returnDate);
        borrowedItems.computeIfAbsent(auisID, k -> new ArrayList<>()).add(lendRecord);

        System.out.println("Notification: Library system has been updated.");

        // notify listeners for each borrow
        for (LibrarySystemListener listener : listeners) {
            listener.onItemBorrowed(auisID, item, lendDate, dueDate, returnDate);
        }

        return true;
    }

    public List<LendRecord> getBorrowedItems(String auisID) {
        return borrowedItems.getOrDefault(auisID, new ArrayList<>());
    }

    public boolean addItem(LibraryItem item) {
        return allItems.add(item);
    }

    private int countBorrowedItems(String auisID) {
        return borrowedItems.getOrDefault(auisID, new ArrayList<>()).size();
    }

    public LibraryItem getLibraryItem(String libraryID) {
        for (LibraryItem item : allItems) {
            if (item.getID().equals(libraryID)) {
                return item;
            }
        }
        return null;
    }

    // add listeners

    public void addListener(LibrarySystemListener listener) {
        listeners.add(listener);
    }
}
