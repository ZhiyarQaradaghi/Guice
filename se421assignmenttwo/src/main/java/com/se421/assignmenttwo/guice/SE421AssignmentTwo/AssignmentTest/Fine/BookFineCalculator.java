package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Fine;

public class BookFineCalculator implements FineCalculator {
    @Override
    public double calculateFine(int daysOverdue) {
        return 3 * daysOverdue;
    }
}