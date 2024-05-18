package com.se421.assignmenttwo.guice.SE421AssignmentTwo.AssignmentTest.System;

// By Zhiyar Burhan Mahmud zb21012@auis.edu.krd and Kevin Aso Faraj ka21027@auis.edu.krd
public class Main {
    public static void main(String[] args) {
        SystemInitializer si = new SystemInitializer();
        si.initialize();
    }
}

// REQUIREMENTS FOR ASSIGNMENT TWO
/**
 * For this assignment, you are required to refactor the code submitted as part
 * of Assignment 1 to do the following:
 * 
 * 1: Incoperate Dependency Injection (DI) design pattern with the use of Google
 * Guice API. DI should be utilized in all of your service classes.
 * 
 * 2: Apply the Listener design pattern on the code submitted for Req. 7 in the
 * original assignment 1.
 * 
 * 3: Make your domain object classes (books, videos, etc) immutable with the
 * help of builder design pattern
 * 
 * 4: Incoperate Singleton design pattern.
 * 
 * 5: Create Java packages and allocate class to them.
 * 
 * Remarks:
 * - Work in a group of two include your names and AUIS email addresses as code
 * comments in the source code files.
 * - Upload a UML class diagram for your new code base.
 * - Upload your project as a zip file to Moodle.
 * 
 * Bonus: +2 In addition to DI, provide another variant of the code base that
 * utilizes Factory design pattern.
 * 
 * 
 */