package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.System;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.LibraryItem;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Lender.LendRecord;

public class LibrarySystem {
    private static LibrarySystem instance;
    private List<LibraryItem> allItems;
    private Map<String, List<LendRecord>> borrowedItems;

    private LibrarySystem() {
        allItems = new ArrayList<>();
        borrowedItems = new HashMap<>();
    }

    public static synchronized LibrarySystem getInstance() {
        if (instance == null) {
            instance = new LibrarySystem();
        }
        return instance;
    }

    public boolean borrowItem(String auisID, LibraryItem item, java.util.Date lendDate, java.util.Date date,
            java.util.Date returnDate) {
        if (countBorrowedItems(auisID) >= 5) {
            System.out.println("You have reached the maximum borrow limit. Return some items to borrow more.");
            return false;
        }
        LendRecord lendRecord = new LendRecord(item, lendDate, date);
        lendRecord.setReturnDate(returnDate);
        borrowedItems.computeIfAbsent(auisID, k -> new ArrayList<>()).add(lendRecord);

        System.out.println("Notification: Library system has been updated.");

        return true;
    }

    public List<LendRecord> getBorrowedItems(String auisID) {
        return borrowedItems.getOrDefault(auisID, Collections.emptyList());
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
        return borrowedItems.getOrDefault(auisID, Collections.emptyList()).size();
    }
}
