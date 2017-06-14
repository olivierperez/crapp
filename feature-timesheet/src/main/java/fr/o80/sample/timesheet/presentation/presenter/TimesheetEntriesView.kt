package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.model.EntriesViewModel

/**
 * @author Olivier Perez
 */
interface TimesheetEntriesView : PresenterView {
    fun update(viewModel: EntriesViewModel?)
    fun showError()
    fun goToCreateProject()
}
