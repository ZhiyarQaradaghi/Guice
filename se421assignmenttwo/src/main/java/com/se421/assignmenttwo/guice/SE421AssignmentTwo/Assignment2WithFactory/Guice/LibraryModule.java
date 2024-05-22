package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithFactory.System.*;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LibrarySystem.class).in(Singleton.class);
        bind(SystemInitializer.class).in(Singleton.class);

        // we dont use DI in this so no more bindings other than Singleton
    }
}
