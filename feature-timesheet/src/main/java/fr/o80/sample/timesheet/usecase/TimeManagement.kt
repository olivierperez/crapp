package fr.o80.sample.timesheet.usecase

import fr.o80.crapp.data.ProjectRepository
import fr.o80.crapp.data.TimesheetRepository
import fr.o80.crapp.data.entity.TimeEntry
import fr.o80.sample.lib.dagger.FeatureScope
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimeManagement @Inject
constructor(private val timesheetRepository: TimesheetRepository, private val projectRepository: ProjectRepository) {

    fun addOneHour(code: String, date: Date): Single<Boolean> {
        return projectRepository.findByCode(code)
                .flatMapSingle { project ->

                    val timeEntry = timesheetRepository.findByProjectAndDate(project, date)
                    if (timeEntry != null) {
                        // Update the existing time entry
                        timeEntry.hours += 1
                        timeEntry.save().map { true }
                    } else {
                        // Create a new time entry
                        TimeEntry(project = project, hours = 1, date = date)
                                .insert()
                                .map { true }
                    }

                }
                .subscribeOn(Schedulers.io())
    }

    fun removeOneHour(code: String, date: Date): Single<Boolean> {
        return projectRepository.findByCode(code)
                .flatMapSingle { project ->
                    val timeEntry = timesheetRepository.findByProjectAndDate(project, date)
                    val hours = timeEntry?.hours ?: -1
                    when (hours) {
                        -1 -> {
                            Single.just(true)
                        }
                        1 -> {
                            timeEntry!!.delete().map { true }
                        }
                        else -> {
                            // Update the existing time entry
                            timeEntry!!.hours--
                            timeEntry.save().map { true }
                        }
                    }
                }
                .subscribeOn(Schedulers.io())
    }
}