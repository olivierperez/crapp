package fr.o80.sample.timesheet.usecase

import javax.inject.Inject

import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.data.TimesheetRepository
import io.reactivex.Observable

/**
 * @author Olivier Perez
 */
@FeatureScope
class ListEntries @Inject
constructor(private val timesheetRepository: TimesheetRepository) {

    fun all(): Observable<List<TimeEntry>> {
        return timesheetRepository.all().toObservable()
    }
}
