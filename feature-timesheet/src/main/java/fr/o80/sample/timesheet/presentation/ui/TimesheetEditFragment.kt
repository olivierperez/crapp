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
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEditFragment : BaseFragment() {

    @Inject
    lateinit var presenter: TimesheetEditPresenter

    val name: EditText by bindView(R.id.timesheet_edit_name)
    val label: EditText by bindView(R.id.timesheet_edit_label)
    val button: Button by bindView(R.id.timesheet_edit_validate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as TimesheetActivity).component().inject(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            presenter.onButtonClicked(name.text.toString(), label.text.toString())
        }

        label.setOnEditorActionListener { _: TextView, idAction: Int, _: KeyEvent? ->
            if (idAction == EditorInfo.IME_ACTION_DONE) {
                presenter.onButtonClicked(name.text.toString(), label.text.toString())
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

    companion object {

        fun create(): TimesheetEditFragment {
            return TimesheetEditFragment()
        }
    }
}
