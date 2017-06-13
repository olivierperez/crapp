package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.model.EntryViewModel
import fr.o80.sample.timesheet.presentation.model.FailedEntryViewModel
import fr.o80.sample.timesheet.presentation.model.LoadedEntryViewModel
import fr.o80.sample.timesheet.presentation.model.LoadingEntryViewModel
import fr.o80.sample.timesheet.usecase.ListEntries
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
                .map<EntryViewModel> { LoadedEntryViewModel(it) }
                .startWith(LoadingEntryViewModel)
                .onErrorReturn { FailedEntryViewModel(it) }
                .subscribe {
                    when (it) {
                        is LoadingEntryViewModel -> view.showLoading()
                        is LoadedEntryViewModel -> {
                            Timber.d("Loaded, %s", it.entries)
                            view.hideLoading()
                            val showFAB = it.entries.size > 4
                            view.showTimeEntries(it.entries, showFAB)
                        }
                        is FailedEntryViewModel -> {
                            Timber.e(it.throwable, "Cannot load time entries")
                            view.hideLoading()
                            view.showError()
                        }
                    }
                }
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
