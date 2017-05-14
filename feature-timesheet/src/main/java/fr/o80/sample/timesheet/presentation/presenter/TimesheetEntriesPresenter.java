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

    @Override
    public void attach(TimesheetEntriesView view) {
        super.attach(view);
        addDisposable(getView()
                .onInit()
                .flatMap(ignored ->
                        listEntries.all()
                                .map(EntryViewModel::success)
                                .startWith(EntryViewModel.inProgress())
                                .onErrorReturn(EntryViewModel::error))
                .subscribe(result -> {
                    if (result.loading()) {
                        view.showLoading();
                    } else if (result.succeeded()) {
                        view.hideLoading();
                        view.showTimeEntries(result.entries());
                    } else {
                        Timber.e(result.throwable(), "Cannot load time entries");
                        view.hideLoading();
                        view.showError();
                    }
                }));
    }

}
