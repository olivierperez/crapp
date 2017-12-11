package fr.o80.sample.timesheet.presentation.ui

import android.os.Bundle
import android.text.InputFilter
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import fr.o80.sample.lib.core.ui.BaseFragment
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEditPresenter
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEditView
import kotlinx.android.synthetic.main.fragment_timesheet_edit.*
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEditFragment : BaseFragment(), TimesheetEditView {

    @Inject
    lateinit var presenter: TimesheetEditPresenter

    override val layoutId: Int = R.layout.fragment_timesheet_edit

    override fun inject() {
        (activity as TimesheetActivity).component().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        timesheet_edit_code.filters = timesheet_edit_code.filters + InputFilter.AllCaps()

        timesheet_edit_validate.setOnClickListener {
            presenter.onButtonClicked(timesheet_edit_label.text.toString(), timesheet_edit_code.text.toString())
        }

        timesheet_edit_label.setOnEditorActionListener { _, idAction, _ ->
            when (idAction) {
                EditorInfo.IME_ACTION_DONE -> {
                    presenter.onButtonClicked(timesheet_edit_label.text.toString(), timesheet_edit_code.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    override fun presenter(): TimesheetEditPresenter = presenter

    override fun finish() {
        (activity as TimesheetActivity).onNewProject()
    }

    override fun showError(errorStr: Int, simpleName: String) {
        Toast.makeText(context, getString(errorStr, simpleName), Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun create(): TimesheetEditFragment {
            return TimesheetEditFragment()
        }
    }
}
