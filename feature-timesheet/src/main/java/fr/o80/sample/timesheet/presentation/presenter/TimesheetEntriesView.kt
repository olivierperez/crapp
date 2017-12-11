package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.timesheet.presentation.model.EntriesViewModel
import java.util.Calendar

/**
 * @author Olivier Perez
 */
interface TimesheetEntriesView : PresenterView {
    fun showLoading()
    fun hideLoading()
    fun update(viewModel: EntriesViewModel)
    fun showError()
    fun goToCreateProject()
    fun showDatePicker(cal: Calendar)
}
