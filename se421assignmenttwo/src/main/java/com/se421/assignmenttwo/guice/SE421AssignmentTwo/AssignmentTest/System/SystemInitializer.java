package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.System;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Book;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Journal;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.LibraryItem;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.TechnicalPaper;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Items.Video;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Lender.LendRecord;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Printer.TablePrinter;

public class SystemInitializer {
    private LibrarySystem librarySystem;

    public SystemInitializer() {
        librarySystem = LibrarySystem.getInstance();
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library System!");
        System.out.println("Select mode:");
        System.out.println("1. Item Lending and Display");
        System.out.println("2. Adding Items");

        int mode = scanner.nextInt();

        if (mode == 1) {
            userMode();
        } else if (mode == 2) {
            libraryAdminMode();
        } else {
            System.out.println("Invalid mode selected. Exiting...");
        }

        scanner.close();
    }

    private void userMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your AUIS ID:");
        String auisID = scanner.next();

        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Choose an action:");
            System.out.println("1. View borrowed items");
            System.out.println("2. Borrow a new item");
            System.out.println("3. Exit");

            if (scanner.hasNextInt()) {
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        viewBorrowedItems(auisID);
                        break;
                    case 2:
                        System.out.println("Enter the library ID of the item you want to borrow:");
                        String libraryID = scanner.next();
                        borrowNewItem(auisID, libraryID);
                        break;
                    case 3:
                        continueLoop = false;
                        break;
                    default:
                        System.out.println("Invalid action. Please choose again.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please choose again.");
                scanner.next();
            }
        }
    }

    private void viewBorrowedItems(String auisID) {
        System.out.println("Fetching borrowed items for " + auisID + ":");
        List<LendRecord> records = librarySystem.getBorrowedItems(auisID);

        if (!records.isEmpty()) {
            TablePrinter.printTable(records);
        } else {
            System.out.println("No borrowed items found for " + auisID + ".");
        }
    }

    private void borrowNewItem(String auisID, String libraryID) {
        LibraryItem item = librarySystem.getLibraryItem(libraryID);

        if (item != null) {
            Scanner scanner = new Scanner(System.in);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

            System.out.println("Enter the lend date (yyyy/MM/dd):");
            Date lendDate = null;
            try {
                lendDate = dateFormat.parse(scanner.next());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy/MM/dd format.");
                return;
            }

            Calendar dueDateCalendar = Calendar.getInstance();
            dueDateCalendar.setTime(lendDate);
            dueDateCalendar.add(Calendar.DATE, 14); // 14 days for fine to begin

            System.out.println("Enter the return date (yyyy/MM/dd):");
            Date returnDate = null;
            try {
                returnDate = dateFormat.parse(scanner.next());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy/MM/dd format.");
                return;
            }

            boolean success = librarySystem.borrowItem(auisID, item, lendDate, dueDateCalendar.getTime(), returnDate);
            if (success) {
                System.out.println("Item successfully borrowed.");
            } else {
                System.out.println("Failed to borrow the item. Please try again later.");
            }
        } else {
            System.out.println("The item with ID " + libraryID + " doesn't exist.");
        }
    }

    private void libraryAdminMode() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Admin Mode:");
            System.out.println("Choose item type:");
            System.out.println("1. Book");
            System.out.println("2. Video");
            System.out.println("3. Journal");
            System.out.println("4. Technical Paper");

            int itemTypeChoice = scanner.nextInt();
            scanner.nextLine();

            LibraryItem item;
            switch (itemTypeChoice) {
                case 1:
                    item = createBook(scanner);
                    break;
                case 2:
                    item = createVideo(scanner);
                    break;
                case 3:
                    item = createJournal(scanner);
                    break;
                case 4:
                    item = createTechnicalPaper(scanner);
                    break;
                default:
                    System.out.println("Invalid item type choice. Please try again.");
                    continue;
            }

            if (item != null) {
                boolean success = librarySystem.addItem(item);
                if (success) {
                    System.out.println("Notification: Library system has been updated.");
                    System.out.println("Item successfully added.");
                } else {
                    System.out.println("Failed to add the item. Please try again later.");
                }
            }

            System.out.println("Do you want to add another item? (yes/no)");
            String continueInput = scanner.nextLine();
            if (continueInput.equalsIgnoreCase("no")) {
                break;
            }
        }
        System.out.println("Returning to select mode...");
        initialize();
        scanner.close();
    }

    private Book createBook(Scanner scanner) {
        System.out.println("Enter item ID:");
        String itemID = scanner.nextLine();

        System.out.println("Enter item title:");
        String title = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        return new Book(itemID, title, description);
    }

    private Video createVideo(Scanner scanner) {
        System.out.println("Enter item ID:");
        String itemID = scanner.nextLine();

        System.out.println("Enter item title:");
        String title = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        return new Video(itemID, title, description);
    }

    private Journal createJournal(Scanner scanner) {
        System.out.println("Enter item ID:");
        String itemID = scanner.nextLine();

        System.out.println("Enter item title:");
        String title = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        return new Journal(itemID, title, description);
    }

    private TechnicalPaper createTechnicalPaper(Scanner scanner) {
        System.out.println("Enter item ID:");
        String itemID = scanner.nextLine();

        System.out.println("Enter item title:");
        String title = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        return new TechnicalPaper(itemID, title, description);
    }
}