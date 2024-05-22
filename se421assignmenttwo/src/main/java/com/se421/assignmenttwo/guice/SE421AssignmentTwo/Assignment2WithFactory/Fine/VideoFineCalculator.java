package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Fine;

public class VideoFineCalculator implements FineCalculator {
    private static final double FINE_RATE_PER_DAY = 1.0;

    @Override
    public double calculateFine(int daysOverdue) {
        return FINE_RATE_PER_DAY * daysOverdue;
    }
}