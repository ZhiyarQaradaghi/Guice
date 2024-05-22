package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Lender;

import com.google.inject.Inject;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine.FineAnnotations.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Items.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LendRecord {
    private LibraryItem item;
    private Date lendDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;
    private FineCalculator fineCalculator;

    @Inject
    public LendRecord(@BookFineAnno FineCalculator bookFineCalculator,
            @JournalFineAnno FineCalculator journalFineCalculator,
            @TechnicalPaperFineAnno FineCalculator technicalPaperFineCalculator,
            @VideoFineAnno FineCalculator videoFineCalculator,
            LibraryItem item, Date lendDate, Date dueDate) {
        this.item = item;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
        this.fine = 0.0;

        if (item instanceof Book) {
            this.fineCalculator = bookFineCalculator;
        } else if (item instanceof Journal) {
            this.fineCalculator = journalFineCalculator;
        } else if (item instanceof TechnicalPaper) {
            this.fineCalculator = technicalPaperFineCalculator;
        } else if (item instanceof Video) {
            this.fineCalculator = videoFineCalculator;
        }
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

            if (fineCalculator != null) {
                return fineCalculator.calculateFine((int) diffInDays);
            }
        }
        return 0.0;
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
