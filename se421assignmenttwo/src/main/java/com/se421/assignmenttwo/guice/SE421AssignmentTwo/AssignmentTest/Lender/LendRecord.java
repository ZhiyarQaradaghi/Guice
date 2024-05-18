package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Lender;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Fine.BookFineCalculator;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Fine.FineCalculator;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Fine.JournalFineCalculator;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Fine.TechnicalPaperFineCalculator;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Fine.VideoFineCalculator;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Book;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Journal;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.LibraryItem;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.TechnicalPaper;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Video;

public class LendRecord {
    private LibraryItem item;
    private Date lendDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;

    public LendRecord(LibraryItem item, Date lendDate, Date dueDate) {
        this.item = item;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
        this.fine = calculateFine();
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        this.fine = calculateFine();
    }

    private double calculateFine() {
        if (returnDate != null && returnDate.after(dueDate)) {
            long diffInMillies = returnDate.getTime() - dueDate.getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            FineCalculator fineCalculator = getFineCalculator();
            if (fineCalculator != null) {
                return fineCalculator.calculateFine((int) diffInDays);
            }
        }
        return 0.0;
    }

    private FineCalculator getFineCalculator() {
        if (item instanceof Book) {
            return new BookFineCalculator();
        } else if (item instanceof Video) {
            return new VideoFineCalculator();
        } else if (item instanceof Journal) {
            return new JournalFineCalculator();
        } else if (item instanceof TechnicalPaper) {
            return new TechnicalPaperFineCalculator();
        }
        return null;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getFine() {
        return calculateFine();
    }

    public LibraryItem getItem() {
        return item;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public void setItem(LibraryItem item) {
        this.item = item;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }
}
