package fr.o80.sample.timesheet.presentation.presenter;

import javax.inject.Inject;

import fr.o80.sample.lib.core.presenter.Presenter;
import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.timesheet.presentation.model.EntryViewModel;
import fr.o80.sample.timesheet.usecase.ListEntries;
import timber.log.Timber;

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
        addDisposable(listEntries.all()
                .map(EntryViewModel::success)
                .startWith(EntryViewModel.inProgress())
                .onErrorReturn(EntryViewModel::error)
                .subscribe(result -> {
                    if (result.loading()) {
                        getView().showLoading();
                    } else if (result.succeeded()) {
                        getView().hideLoading();
                        getView().showTimeEntries(result.entries());
                    } else {
                        Timber.e(result.throwable(), "Cannot load time entries");
                        getView().hideLoading();
                        getView().showError();
                    }
                }));
    }
}
