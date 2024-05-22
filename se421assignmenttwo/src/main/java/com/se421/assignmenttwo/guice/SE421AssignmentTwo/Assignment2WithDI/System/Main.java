package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.System;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Guice.LibraryModule;

// By Zhiyar Burhan Mahmud zb21012@auis.edu.krd and Kevin Aso Faraj ka21027@auis.edu.krd
public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new LibraryModule());
        SystemInitializer si = injector.getInstance(SystemInitializer.class);
        si.initialize();
    }
}
