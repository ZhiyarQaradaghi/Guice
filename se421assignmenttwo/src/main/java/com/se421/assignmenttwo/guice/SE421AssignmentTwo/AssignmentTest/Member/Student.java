package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.Member;

public class Student implements LibraryMember {
    private String auisID;

    public Student(String auisID) {
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