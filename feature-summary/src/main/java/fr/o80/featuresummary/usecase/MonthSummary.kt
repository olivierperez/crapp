package fr.o80.featuresummary.usecase

import fr.o80.crapp.data.ProjectRepository
import fr.o80.crapp.data.TimesheetRepository
import fr.o80.crapp.data.entity.Project
import fr.o80.crapp.data.entity.TimeEntry
import fr.o80.featuresummary.usecase.model.ProjectSummary
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.utils.firstDayOfMonth
import fr.o80.sample.lib.utils.lastDayOfMonth
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class MonthSummary @Inject
constructor(private val timesheetRepository: TimesheetRepository, private val projectRepository: ProjectRepository) {

    fun getMonth(startDate: Date): Single<List<ProjectSummary>> {
        val start = firstDayOfMonth(startDate)
        val end = lastDayOfMonth(startDate)

        val projectsSingle = projectRepository.all()
        val entriesSingle = timesheetRepository
                .findByDateRange(start, end)
                .flatMapObservable { Observable.fromIterable(it) }
                .toMultimap { it.project!!.id }

        return Single.zip<List<Project>, MutableMap<Long, MutableCollection<TimeEntry>>, List<ProjectSummary>>(
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
                                                ?.map(TimeEntry::hours)
                                                ?.fold(0) { acc, item -> acc + item }
                                                ?: 0
                                )
                            }
                })
                .subscribeOn(Schedulers.io())
    }
}