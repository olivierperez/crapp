package fr.o80.sample.timesheet.dagger;

import dagger.Component;
import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.lib.di.LibComponent;
import fr.o80.sample.timesheet.presentation.ui.TimesheetEntriesFragment;

/**
 * @author Olivier Perez
 */
@Component(dependencies = LibComponent.class, modules = TimesheetModule.class)
@FeatureScope
public interface TimesheetComponent {
    void inject(TimesheetEntriesFragment fragment);
}
