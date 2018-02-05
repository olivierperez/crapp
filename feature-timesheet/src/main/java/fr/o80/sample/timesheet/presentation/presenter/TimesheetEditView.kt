package fr.o80.sample.timesheet.presentation.presenter

import android.support.annotation.StringRes
import fr.o80.sample.lib.core.presenter.PresenterView

/**
 * @author Olivier Perez
 */
interface TimesheetEditView : PresenterView {
    fun finish()
    fun showError(@StringRes errorStr: Int, simpleName: String)
    fun initFields(label: String, code: String)
    fun setValidateButton(@StringRes validateText: Int)
}