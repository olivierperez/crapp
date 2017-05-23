package fr.o80.sample.timesheet.dagger;

import dagger.Module;
import dagger.Provides;
import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.timesheet.presentation.data.TimesheetRepository;

/**
 * @author Olivier Perez
 */
@Module
public class TimesheetModule {

    @Provides
    @FeatureScope
    public TimesheetRepository provideTimesheetRepository() {
        return new TimesheetRepository();
    }
}
