package fr.o80.sample.timesheet.dagger;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import dagger.Module;
import dagger.Provides;
import fr.o80.sample.timesheet.data.entity.TimeEntry;

/**
 * @author Olivier Perez
 */
@Module
public class TimesheetModule {

    @Provides
    public ModelAdapter<TimeEntry> provideTimeEntryModelAdapter() {
        return FlowManager.getModelAdapter(TimeEntry.class);
    }
}
