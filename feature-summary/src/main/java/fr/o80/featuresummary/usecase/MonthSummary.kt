package fr.o80.featuresummary.usecase

import fr.o80.crapp.data.ProjectRepository
import fr.o80.crapp.data.TimesheetRepository
import fr.o80.featuresummary.usecase.model.ProjectSummary
import fr.o80.featuresummary.usecase.model.SummaryTimeEntry
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.utils.CalendarUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class MonthSummary @Inject
constructor(private val timesheetRepository: TimesheetRepository, private val projectRepository: ProjectRepository) {

    fun getMonth(startDate: Date): Single<List<ProjectSummary>> {
        val start = CalendarUtils.firstDayOfMonth(startDate)
        val end = CalendarUtils.lastDayOfMonth(startDate)

        val projectsSingle = projectRepository.all()
        val entriesSingle = timesheetRepository
                .findByDateRange(start, end)
                .flatMapObservable { Observable.fromIterable(it) }
                .toMultimap { it.project!!.id }

        return Single.zip(
                projectsSingle,
                entriesSingle,
                BiFunction { projects, entries ->
                    projects
                            .filter { (id) -> entries.containsKey(id) }
                            .map { (id, label, code) ->
                                ProjectSummary(
                                        label!!,
                                        code!!,
                                        entries[id]
                                                ?.map { (_, _, date, hours) -> SummaryTimeEntry(date!!, hours) }
                                                ?: listOf()
                                              )
                            }
                })
    }
}