package fr.o80.sample.timesheet.dagger

import dagger.Module
import dagger.Provides
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.presentation.data.TimesheetRepository

/**
 * @author Olivier Perez
 */
@Module
class TimesheetModule {

    @Provides
    @FeatureScope
    fun provideTimesheetRepository(): TimesheetRepository {
        return TimesheetRepository()
    }
}
