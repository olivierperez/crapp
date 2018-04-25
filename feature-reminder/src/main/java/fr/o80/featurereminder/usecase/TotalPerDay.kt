package fr.o80.featurereminder.usecase

import fr.o80.crapp.data.TimesheetRepository
import fr.o80.crapp.data.entity.TimeEntry
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.utils.plus
import io.reactivex.Observable
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

@FeatureScope
class TotalPerDay
@Inject constructor(private val timesheetRepository: TimesheetRepository) {
    fun today(): Single<Int> {
        val now = Date()
        return timesheetRepository.findByDateRange(now, now + 1)
                .flatMapObservable { Observable.fromIterable(it) }
                .map(TimeEntry::hours)
                .sum()
    }
}

private fun Observable<Int>.sum(): Single<Int> = collectInto(0) { prev, elem -> prev + elem }
