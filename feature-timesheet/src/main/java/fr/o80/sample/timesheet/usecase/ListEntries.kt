package fr.o80.sample.timesheet.usecase

import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.TimesheetRepository
import fr.o80.sample.timesheet.data.entity.TimeEntry
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class ListEntries @Inject
constructor(private val timesheetRepository: TimesheetRepository) {

    fun all(): Single<List<TimeEntry>> {
        return timesheetRepository.all()
    }
}
