package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Member;

public class StaffMember implements LibraryMember {
    private String auisID;

    public StaffMember(String auisID) {
        this.auisID = auisID;
    }

    @Override
    public String getID() {
        return auisID;
    }

    @Override
    public int getMaxBorrowLimit() {
        return 5;
    }
}