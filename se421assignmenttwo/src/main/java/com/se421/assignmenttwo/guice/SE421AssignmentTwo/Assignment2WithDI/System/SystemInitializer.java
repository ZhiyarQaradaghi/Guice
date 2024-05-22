package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.System;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.inject.Inject;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Items.ItemBuilder;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Items.LibraryItem;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Lender.LendRecord;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Listener.LibrarySystemListener;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Printer.TablePrinter;

public class SystemInitializer implements LibrarySystemListener {
    private LibrarySystem librarySystem;

    @Inject
    public SystemInitializer(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
        this.librarySystem.addListener(this); // this class is a listener
    }

    @Override
    public void onItemBorrowed(String auisID, LibraryItem item, Date lendDate, Date dueDate, Date returnDate) {
        // listener for borrwing items
        System.out.println("Item borrowed details:");
        System.out.println("AUIS ID: " + auisID);
        System.out.println("Item: " + item.getType());
        System.out.println("Lend Date: " + lendDate.toString());
        System.out.println("Due Date: " + dueDate.toString());
        System.out.println("Return Date: " + returnDate.toString());
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library System!");
        System.out.println("Select mode:");
        System.out.println("1. Item Lending and Display");
        System.out.println("2. Adding Items");

        int mode = scanner.nextInt();

        if (mode == 1) {
            readUserInput();
        } else if (mode == 2) {
            readAdminInput();
        } else {
            System.out.println("Invalid mode selected. Exiting...");
        }

        scanner.close();
    }

    private void readUserInput() {
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

    private void readAdminInput() {
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
            ItemBuilder itemBuilder = new ItemBuilder("");

            switch (itemTypeChoice) {
                case 1:
                    item = createItem(scanner, itemBuilder, "Book");
                    break;
                case 2:
                    item = createItem(scanner, itemBuilder, "Video");
                    break;
                case 3:
                    item = createItem(scanner, itemBuilder, "Journal");
                    break;
                case 4:
                    item = createItem(scanner, itemBuilder, "TechnicalPaper");
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

    private LibraryItem createItem(Scanner scanner, ItemBuilder itemBuilder, String itemType) {
        System.out.println("Enter item ID:");
        String itemID = scanner.nextLine();

        System.out.println("Enter item title:");
        String title = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        itemBuilder.setID(itemID)
                .setTitle(title)
                .setDescription(description);

        switch (itemType) {
            case "Book":
                return itemBuilder.buildBook();
            case "Video":
                return itemBuilder.buildVideo();
            case "Journal":
                return itemBuilder.buildJournal();
            case "TechnicalPaper":
                return itemBuilder.buildTechnicalPaper();
            default:
                return null;
        }
    }
}