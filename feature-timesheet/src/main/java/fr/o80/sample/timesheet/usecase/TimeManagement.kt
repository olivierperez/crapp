package fr.o80.sample.timesheet.usecase

import fr.o80.crapp.data.ProjectRepository
import fr.o80.crapp.data.TimesheetRepository
import fr.o80.crapp.data.entity.TimeEntry
import fr.o80.sample.lib.dagger.FeatureScope
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
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
        return Single
                .create<Boolean> { emitter ->
                    val project = projectRepository.findByCode(code)

                    if (project != null) {
                        val timeEntry = timesheetRepository.findByProjectAndDate(project, date)
                        if (timeEntry != null) {
                            // Update the existing time entry
                            timeEntry.hours += 1
                            timeEntry.save()
                                    .subscribe(
                                            { emitter.onSuccess(true) },
                                            { emitter.onError(it) })
                        } else {
                            // Create a new time entry
                            TimeEntry(project = project, hours = 1, date = date)
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

    fun removeOneHour(code: String, date: Date): Single<Boolean> {
        return Single
                .create<Boolean> { emitter ->
                    val project = projectRepository.findByCode(code)

                    if (project != null) {
                        val timeEntry = timesheetRepository.findByProjectAndDate(project, date)
                        when (timeEntry?.hours ?: -1) {
                            -1 -> {
                                emitter.onSuccess(true)
                            }
                            1 -> {
                                timeEntry!!.delete().subscribeBy(
                                        onSuccess = { emitter.onSuccess(true) },
                                        onError = { emitter.onError(it) })
                            }
                            else -> {
                                // Update the existing time entry
                                timeEntry!!.hours--
                                timeEntry.save().subscribeBy(
                                        onSuccess = { emitter.onSuccess(true) },
                                        onError = { emitter.onError(it) })
                            }
                        }
                    } else {
                        emitter.onError(RuntimeException("Project not found"))
                    }
                }
                .subscribeOn(Schedulers.io())
    }
}