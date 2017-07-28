package fr.o80.sample.timesheet.usecase

import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.ProjectRepository
import fr.o80.sample.timesheet.data.TimesheetRepository
import fr.o80.sample.timesheet.data.entity.TimeEntry
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimeManagement @Inject
constructor(private val timesheetRepository: TimesheetRepository, private val projectRepository: ProjectRepository) {
    fun addOneHour(code: String): Single<TimeEntry> {
        return Single
                .create<TimeEntry> { emitter ->
                    val project = projectRepository.findByCode(code)

                    if (project != null) {
                        val timeEntry = timesheetRepository.findByProject(project)
                        if (timeEntry != null) {
                            // Update the existing time entry
                            timeEntry.hours += 1
                            timeEntry.save().subscribe(
                                    { emitter.onSuccess(timeEntry) },
                                    { emitter.onError(it) })
                        } else {
                            // Create a new time entry
                            val newTimeEntry = TimeEntry(project = project, hours = 1)
                            newTimeEntry.insert().subscribe(
                                    { emitter.onSuccess(newTimeEntry) },
                                    { emitter.onError(it) })
                        }
                    } else {
                        emitter.onError(RuntimeException("Project not found"))
                    }
                }
                .subscribeOn(Schedulers.io())
    }
}