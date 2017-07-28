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

    fun addOneHour(code: String): Single<Boolean> {
        return Single
                .create<Boolean> { emitter ->
                    val project = projectRepository.findByCode(code)

                    if (project != null) {
                        val timeEntry = timesheetRepository.findByProject(project)
                        if (timeEntry != null) {
                            // Update the existing time entry
                            timeEntry.hours += 1
                            timeEntry.save()
                                    .subscribe(
                                            { emitter.onSuccess(true) },
                                            { emitter.onError(it) })
                        } else {
                            // Create a new time entry
                            TimeEntry(project = project, hours = 1)
                                    .insert()
                                    .subscribe(
                                            { emitter.onSuccess(true) },
                                            { emitter.onError(it) })
                        }
                    } else {
                        emitter.onError(RuntimeException("Project not found"))
                    }
                }
                .subscribeOn(Schedulers.io())
    }

    fun removeOneHour(code: String): Single<Boolean> {
        return Single
                .create<Boolean> { emitter ->
                    val project = projectRepository.findByCode(code)

                    if (project != null) {
                        val timeEntry = timesheetRepository.findByProject(project)
                        if (timeEntry?.hours ?: 0 >= 1) {
                            // Update the existing time entry
                            timeEntry!!.hours -= 1
                            timeEntry!!.save()
                                    .subscribe(
                                            { emitter.onSuccess(true) },
                                            { emitter.onError(it) })
                        } else {
                            // Not yet time entry
                            emitter.onSuccess(true)
                        }
                    } else {
                        emitter.onError(RuntimeException("Project not found"))
                    }
                }
                .subscribeOn(Schedulers.io())
    }
}