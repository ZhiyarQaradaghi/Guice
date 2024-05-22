package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Listener;

import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.LibraryItem;

public interface LibrarySystemListener {
    void onItemBorrowed(String auisID, LibraryItem item, java.util.Date lendDate, java.util.Date dueDate,
            java.util.Date returnDate);
}
