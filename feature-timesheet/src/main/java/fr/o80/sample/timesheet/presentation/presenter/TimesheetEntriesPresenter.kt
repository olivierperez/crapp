package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.Project
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.model.*
import fr.o80.sample.timesheet.usecase.ListEntries
import fr.o80.sample.timesheet.usecase.ProjectCrud
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetEntriesPresenter
@Inject
constructor(private val listEntries: ListEntries, private val projectCrud: ProjectCrud)
    : Presenter<TimesheetEntriesView>() {

    fun init() {
        Timber.d("init")
        addDisposable(Single
                .zip(listEntries.all(), projectCrud.all(), BiFunction {
                    entries: List<TimeEntry>, projects: List<Project> ->
                    toEntryViews(entries, projects)
                })
                .map<EntriesViewModel> { LoadedEntriesViewModel(it) }
                .toObservable()
                .startWith(LoadingEntriesViewModel)
                .onErrorReturn { FailedEntriesViewModel(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::update)
        )
    }

    private fun toEntryViews(entries: List<TimeEntry>, projects: List<Project>): List<EntryViewModel> {
        TODO("not implemented")
    }

    fun onTimeEntryClicked(timeEntry: TimeEntry) {
        Timber.d("Time entry clicked: %s", timeEntry.project?.label)
    }

    fun onAddClicked() {
        Timber.d("Add time entry")
        view.goToCreateProject()
    }
}
