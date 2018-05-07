package fr.o80.featurereminder.usecase

import fr.o80.crapp.data.TimesheetRepository
import fr.o80.crapp.data.entity.TimeEntry
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.utils.plus
import fr.o80.sample.lib.utils.today
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class TotalPerDay
@Inject constructor(private val timesheetRepository: TimesheetRepository) {
    fun getTodayHours(): Single<Int> {
        val today = today()
        return timesheetRepository.findByDateRange(today, today + 1)
                .map { it.sumBy(TimeEntry::hours) }
    }
}
