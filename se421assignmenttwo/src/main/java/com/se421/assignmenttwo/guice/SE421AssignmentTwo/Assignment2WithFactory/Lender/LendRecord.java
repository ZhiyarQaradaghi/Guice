package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Lender;

import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.Book;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.Journal;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.LibraryItem;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.TechnicalPaper;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Items.Video;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Fine.*;

import java.util.Date;

public class LendRecord {
    private final String auisID;
    private final LibraryItem item;
    private final Date lendDate;
    private final Date dueDate;
    private Date returnDate;
    private FineCalculator fineCalculator;

    public LendRecord(String auisID, LibraryItem item, Date lendDate, Date dueDate) {
        this.auisID = auisID;
        this.item = item;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fineCalculator = getFineCalculator(item);
    }

    public String getAuisID() {
        return auisID;
    }

    public LibraryItem getItem() {
        return item;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        if (returnDate == null || !returnDate.after(dueDate)) {
            return 0.0;
        }

        long diffInMillies = Math.abs(returnDate.getTime() - dueDate.getTime());
        int diffInDays = (int) (diffInMillies / (1000 * 60 * 60 * 24));

        return fineCalculator.calculateFine(diffInDays);
    }

    private FineCalculator getFineCalculator(LibraryItem item) {
        if (item instanceof Book) {
            return new BookFineCalculator();
        } else if (item instanceof Journal) {
            return new JournalFineCalculator();
        } else if (item instanceof Video) {
            return new VideoFineCalculator();
        } else if (item instanceof TechnicalPaper) {
            return new TechnicalPaperFineCalculator();
        } else {
            throw new IllegalArgumentException("Unknown item type");
        }
    }
}
