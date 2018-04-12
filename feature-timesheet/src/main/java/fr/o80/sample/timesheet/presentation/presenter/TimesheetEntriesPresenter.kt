package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.presentation.model.EntriesViewModel
import fr.o80.sample.timesheet.presentation.model.FailedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadingEntriesViewModel
import fr.o80.sample.timesheet.usecase.ListEntries
import fr.o80.sample.timesheet.usecase.TimeManagement
import fr.o80.sample.lib.utils.mkdate
import fr.o80.sample.lib.utils.plus
import fr.o80.sample.timesheet.usecase.model.EntryViewModel
import fr.o80.sample.lib.utils.today
import fr.o80.sample.timesheet.Const.HOURS_PER_DAY
import fr.o80.sample.timesheet.usecase.ProjectCrud
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetEntriesPresenter
@Inject
constructor(private val listEntries: ListEntries, private val timeManagement: TimeManagement, private val projectCrud: ProjectCrud)
    : Presenter<TimesheetEntriesView>() {

    var cache: LoadedEntriesViewModel? = null

    fun init() {
        Timber.d("init")
        load(cache?.date ?: today())
    }

    private fun load(date: Date) {
        addDisposable(listEntries.findByDate(date, date + 1)
                .map<EntriesViewModel> {
                    LoadedEntriesViewModel(
                            it,
                            it.foldRight(0) { elem, acc -> acc + elem.hours },
                            date)
                }
                .doOnSuccess {
                    if (it is LoadedEntriesViewModel) {
                        cache = it
                    }
                }
                .toObservable()
                .startWith(LoadingEntriesViewModel)
                .onErrorReturn {
                    Timber.e(it, "Failed to load time entries for date %s", date)
                    FailedEntriesViewModel(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Timber.d("On next $it")
                    view.update(it)
                }
        )
    }

    fun onTimeEntryClicked(timeEntry: EntryViewModel) {
        Timber.d("Time entry clicked: %s, %s, %d", timeEntry.label, timeEntry.code, timeEntry.hours)
        addDisposable(projectCrud.findByCode(timeEntry.code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { view.goToEditProject(it) },
                        onError = {throwable ->
                            Timber.e(throwable, "Failed to open project edition")
                        }))

    }

    fun onTimeAdded(timeEntry: EntryViewModel) {
        Timber.d("Time added: %s, %s, %d", timeEntry.label, timeEntry.code, timeEntry.hours)
        addDisposable(timeManagement.addHours(timeEntry.code, cache!!.date, 1)
                .subscribeBy(
                        onSuccess = {
                            Timber.d("One hour added")
                            init()
                        },
                        onError = {
                            Timber.e(it, "Failed to add one hour")
                        }))
    }

    fun onLongTimeAdded(timeEntry: EntryViewModel) : Boolean {
        val missingHoursToFillDay = HOURS_PER_DAY - cache!!.totalHours
        addDisposable(timeManagement.addHours(timeEntry.code, cache!!.date, missingHoursToFillDay)
                              .subscribeBy(
                                      onSuccess = {
                                          Timber.d("$missingHoursToFillDay hour(s) added")
                                          init()
                                      },
                                      onError = {
                                          Timber.e(it, "Failed to add $missingHoursToFillDay hour(s)")
                                      }))
        return true
    }

    fun onTimeRemoved(timeEntry: EntryViewModel) {
        Timber.d("Time removed: %s, %s, %d", timeEntry.label, timeEntry.code, timeEntry.hours)
        addDisposable(timeManagement.removeHours(timeEntry.code, cache!!.date, 1)
                .subscribeBy(
                        onSuccess = {
                            Timber.d("One hour removed")
                            init()
                        },
                        onError = {
                            Timber.e(it, "Failed to remove one hour")
                        }))
    }

    fun onLongTimeRemoved(timeEntry: EntryViewModel) : Boolean {
        Timber.d("Time removed: %s, %s, %d", timeEntry.label, timeEntry.code, timeEntry.hours)
        addDisposable(timeManagement.removeHours(timeEntry.code, cache!!.date, timeEntry.hours)
                              .subscribeBy(
                                      onSuccess = {
                                          Timber.d("${timeEntry.hours} hour(s) removed")
                                          init()
                                      },
                                      onError = {
                                          Timber.e(it, "Failed to remove ${timeEntry.hours} hour(s)")
                                      }))
        return true
    }

    fun onAddClicked() {
        Timber.d("Add time entry")
        view.goToCreateProject()
    }

    fun onCurrentDateClicked() {
        cache?.let {
            val cal = Calendar.getInstance().apply { time = it.date }
            view.showDatePicker(cal)
        }
    }

    fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        cache?.let {
            load(mkdate(year, month, dayOfMonth))
        }
    }

}
