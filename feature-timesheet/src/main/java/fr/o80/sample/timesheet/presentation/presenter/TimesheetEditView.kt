package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.timesheet.data.entity.Project

/**
 * @author Olivier Perez
 */
interface TimesheetEditView : PresenterView {
    fun finishWithProject(project: Project)
}