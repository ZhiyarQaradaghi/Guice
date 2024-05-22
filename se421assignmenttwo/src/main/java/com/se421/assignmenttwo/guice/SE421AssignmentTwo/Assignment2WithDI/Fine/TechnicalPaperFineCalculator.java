package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine;

public class TechnicalPaperFineCalculator implements FineCalculator {
    private static final double FINE_RATE_PER_DAY = 2.0;

    @Override
    public double calculateFine(int daysOverdue) {
        return FINE_RATE_PER_DAY * daysOverdue;
    }
}