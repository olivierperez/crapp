package fr.o80.sample.timesheet.presentation.presenter;

import javax.inject.Inject;

import fr.o80.sample.lib.core.presenter.Presenter;
import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.timesheet.usecase.ListEntries;

/**
 * @author Olivier Perez
 */
@FeatureScope
public class TimesheetEntriesPresenter extends Presenter<TimesheetEntriesView> {

    private final ListEntries listEntries;

    @Inject
    public TimesheetEntriesPresenter(ListEntries listEntries) {
        this.listEntries = listEntries;
    }

    public void init() {
        listEntries.all();
    }
}
