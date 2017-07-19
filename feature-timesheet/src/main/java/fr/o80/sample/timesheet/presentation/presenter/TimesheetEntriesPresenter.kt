package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.model.EntriesViewModel
import fr.o80.sample.timesheet.presentation.model.FailedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadingEntriesViewModel
import fr.o80.sample.timesheet.usecase.ListEntries
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetEntriesPresenter @Inject
constructor(private val listEntries: ListEntries) : Presenter<TimesheetEntriesView>() {

    fun init() {
        Timber.d("init")
        addDisposable(listEntries.all()
                .map<EntriesViewModel> { LoadedEntriesViewModel(it) }
                .startWith(LoadingEntriesViewModel)
                .onErrorReturn { FailedEntriesViewModel(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::update)
        )
    }

    fun onTimeEntryClicked(timeEntry: TimeEntry) {
        Timber.d("Time entry clicked: %s", timeEntry.project?.label)
    }

    fun onAddClicked() {
        Timber.d("Add time entry")
        view.goToCreateProject()
    }
}
