package fr.o80.sample.timesheet.usecase

import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.ProjectRepository
import fr.o80.sample.timesheet.data.TimesheetRepository
import fr.o80.sample.timesheet.usecase.model.EntryViewModel
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class ListEntries @Inject
constructor(private val timesheetRepository: TimesheetRepository, private val projectRepository: ProjectRepository) {

    fun findByDate(after: Date, strictlyBefore: Date): Single<List<EntryViewModel>> {
        return Single
                .zip(timesheetRepository.findByDateRange(after, strictlyBefore), projectRepository.all(), BiFunction {
                    entries, projects ->
                    val vms = mutableListOf<EntryViewModel>()
                    vms.addAll(entries.map { EntryViewModel(it.project!!.label!!, it.project!!.code!!, it.hours, it.date!!) })
                    vms.addAll(projects
                                       .filter { project ->
                                           entries.find { entry -> entry.project?.id == project.id } == null
                                       }
                                       .map { EntryViewModel(it.label!!, it.code!!, 0, Date()) })

                    vms.sortBy { it.label }
                    vms.toList()
                })
    }
}
