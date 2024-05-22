package com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.Fine.FineAnnotations.*;
import com.se421.assignmenttwo.guice.SE421AssignmentTwo.Assignment2WithDI.System.*;

public class LibraryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LibrarySystem.class).in(Singleton.class);
        bind(SystemInitializer.class).in(Singleton.class);

        bind(FineCalculator.class).annotatedWith(BookFineAnno.class).to(BookFineCalculator.class);
        bind(FineCalculator.class).annotatedWith(JournalFineAnno.class).to(JournalFineCalculator.class);
        bind(FineCalculator.class).annotatedWith(TechnicalPaperFineAnno.class).to(TechnicalPaperFineCalculator.class);
        bind(FineCalculator.class).annotatedWith(VideoFineAnno.class).to(VideoFineCalculator.class);

    }
}
