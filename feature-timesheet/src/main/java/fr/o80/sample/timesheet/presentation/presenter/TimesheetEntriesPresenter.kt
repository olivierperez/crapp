package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.presentation.model.EntriesViewModel
import fr.o80.sample.timesheet.presentation.model.FailedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadingEntriesViewModel
import fr.o80.sample.timesheet.usecase.ListEntries
import fr.o80.sample.timesheet.usecase.TimeManagement
import fr.o80.sample.timesheet.usecase.model.EntryViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
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
constructor(private val listEntries: ListEntries, private val timeManagement: TimeManagement)
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
                                  Timber.e(it, "Cannot load entries")
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
    }

    fun onTimeAdded(timeEntry: EntryViewModel) {
        Timber.d("Time added: %s, %s, %d", timeEntry.label, timeEntry.code, timeEntry.hours)
        timeManagement.addOneHour(timeEntry.code, cache!!.date)
                .subscribe({
                               Timber.d("One hour added")
                               init()
                           }, {
                               Timber.e(it, "Failed to add one hour")
                           })
    }

    fun onTimeRemoved(timeEntry: EntryViewModel) {
        Timber.d("Time removed: %s, %s, %d", timeEntry.label, timeEntry.code, timeEntry.hours)
        timeManagement.removeOneHour(timeEntry.code, cache!!.date)
                .subscribe({
                               Timber.d("One hour removed")
                               init()
                           }, {
                               Timber.e(it, "Failed to remove one hour")
                           })
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
            val newDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.time

            load(newDate)
        }
    }

    private fun today(): Date =
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.time

    operator fun Date.plus(days: Int): Date =
            Calendar.getInstance()
                    .apply {
                        time = this@plus
                        add(Calendar.DAY_OF_MONTH, days)
                    }.time

}
