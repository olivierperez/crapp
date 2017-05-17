package fr.o80.sample.timesheet.usecase;

import java.util.List;

import javax.inject.Inject;

import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.timesheet.data.entity.TimeEntry;
import fr.o80.sample.timesheet.presentation.data.TimesheetRepository;
import io.reactivex.Observable;

/**
 * @author Olivier Perez
 */
@FeatureScope
public class ListEntries {

    private final TimesheetRepository timesheetRepository;

    @Inject
    public ListEntries(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public Observable<List<TimeEntry>> all() {
        return timesheetRepository.all().toObservable();
    }
}
