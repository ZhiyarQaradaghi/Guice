package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine;

public class BookFineCalculator implements FineCalculator {
    @Override
    public double calculateFine(int daysOverdue) {
        return 3 * daysOverdue;
    }
}