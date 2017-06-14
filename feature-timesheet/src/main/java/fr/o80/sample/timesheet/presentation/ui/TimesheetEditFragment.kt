package fr.o80.sample.timesheet.presentation.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fr.o80.sample.lib.core.ui.BaseFragment
import fr.o80.sample.lib.core.ui.bindView
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEditPresenter
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEditView
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEditFragment : BaseFragment(), TimesheetEditView {

    @Inject
    lateinit var presenter: TimesheetEditPresenter

    val code: EditText by bindView(R.id.timesheet_edit_name)
    val label: EditText by bindView(R.id.timesheet_edit_label)
    val button: Button by bindView(R.id.timesheet_edit_validate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as TimesheetActivity).component().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            presenter.onButtonClicked(code.text.toString(), label.text.toString())
        }

        label.setOnEditorActionListener { _: TextView, idAction: Int, _: KeyEvent? ->
            if (idAction == EditorInfo.IME_ACTION_DONE) {
                presenter.onButtonClicked(code.text.toString(), label.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int = R.layout.fragment_timesheet_edit

    override fun presenter(): TimesheetEditPresenter = presenter

    override fun finish() {
        (activity as TimesheetActivity).onNewProject()
    }

    companion object {

        fun create(): TimesheetEditFragment {
            return TimesheetEditFragment()
        }
    }
}
