package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Printer;

import java.text.SimpleDateFormat;
import java.util.*;

import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Book;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Journal;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.LibraryItem;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.TechnicalPaper;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Video;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Lender.LendRecord;

public class TablePrinter {
    public static void printTable(List<LendRecord> records) {
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s %-35s %-35s %-15s %-15s %-10s\n", "Item ID", "Title", "Description", "Lend Date",
                "Return Date", "Fine");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        for (LendRecord record : records) {
            String itemType = getItemType(record.getItem());
            String itemID = record.getItem().getID();
            String title = record.getItem().getTitle();
            String description = record.getItem().getDescription();
            String lendDate = dateFormat.format(record.getLendDate());
            String returnDate = record.getReturnDate() != null ? dateFormat.format(record.getReturnDate())
                    : "Not returned";
            double fine = record.getFine();

            boolean overdue = record.getReturnDate() != null && record.getReturnDate().after(record.getDueDate());

            if (overdue) {
                System.out.print("\u001B[31m");
            }
            System.out.printf("%-25s %-35s %-35s %-15s %-15s $%.2f\n", itemType + " - " + itemID, title, description,
                    lendDate, returnDate, fine);
            if (overdue) {
                System.out.print("\u001B[0m");
            }
        }

        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static String getItemType(LibraryItem item) {
        if (item instanceof Book) {
            return "Book";
        } else if (item instanceof Video) {
            return "Video";
        } else if (item instanceof Journal) {
            return "Journal";
        } else if (item instanceof TechnicalPaper) {
            return "Technical Paper";
        } else {
            return "Unknown";
        }
    }
}