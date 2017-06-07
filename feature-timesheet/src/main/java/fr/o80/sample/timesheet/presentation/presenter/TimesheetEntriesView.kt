package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.timesheet.data.entity.TimeEntry

/**
 * @author Olivier Perez
 */
interface TimesheetEntriesView : PresenterView {
    fun showTimeEntries(entries: List<TimeEntry>, showFAB: Boolean)
    fun showError()
    fun goToCreateProject()
}
