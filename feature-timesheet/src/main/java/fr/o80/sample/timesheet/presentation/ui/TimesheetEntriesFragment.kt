package fr.o80.sample.timesheet.presentation.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import fr.o80.crapp.data.entity.Project
import fr.o80.sample.lib.core.ui.BaseFragment
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.presentation.model.EntriesViewModel
import fr.o80.sample.timesheet.presentation.model.FailedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadedEntriesViewModel
import fr.o80.sample.timesheet.presentation.model.LoadingEntriesViewModel
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesPresenter
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesView
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.fragment_timesheet_entries.*
import timber.log.Timber
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEntriesFragment : BaseFragment(), TimesheetEntriesView {

    @Inject
    lateinit var presenter: TimesheetEntriesPresenter

    private var datePickerDialog: DatePickerDialog? = null

    private val adapter: TimesheetAdapter by lazy {
        TimesheetAdapter(
                onClick = presenter::onTimeEntryClicked,
                onTimeAdded = presenter::onTimeAdded,
                onLongTimeAdded = presenter::onLongTimeAdded,
                onTimeRemoved = presenter::onTimeRemoved,
                onLongTimeRemoved = presenter::onLongTimeRemoved
                        )
    }

    override val layoutId: Int
        get() = R.layout.fragment_timesheet_entries

    override fun presenter(): TimesheetEntriesPresenter = presenter

    override fun inject() {
        (activity as TimesheetActivity).component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)

        activity.supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        fab.setOnClickListener { presenter.onAddClicked() }
        recyclerView.itemAnimator = SlideInLeftAnimator()
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter

        presenter.init()
    }

    override fun update(viewModel: EntriesViewModel) {
        when (viewModel) {
            LoadingEntriesViewModel -> showLoading()

            is LoadedEntriesViewModel -> {
                Timber.d("Loaded, %s", viewModel.entries)
                hideLoading()

                adapter.setEntries(viewModel.entries)

                totalHours.text = getString(R.string.hours, viewModel.totalHours)
                currentDate.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(viewModel.date)

                currentDate.setOnClickListener { presenter.onCurrentDateClicked() }
            }

            is FailedEntriesViewModel -> {
                Timber.e(viewModel.throwable, "Cannot load time entries")
                hideLoading()
                showError()
            }
        }
    }

    override fun showDatePicker(cal: Calendar) {
        val picker = datePickerDialog ?: DatePickerDialog(
                activity,
                { _, year, month, dayOfMonth ->
                    presenter.onDateSelected(year, month, dayOfMonth)
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

        if (!picker.isShowing) picker.show()

        datePickerDialog = picker
    }

    override fun showError() {
        //TODO("not implemented")
    }

    override fun goToCreateProject() {
        (activity as TimesheetActivity).createProject()
    }

    override fun goToEditProject(project: Project) {
        (activity as TimesheetActivity).editProject(project)
    }

    override fun showLoading() {
        //TODO("not implemented")
    }

    override fun hideLoading() {
        //TODO("not implemented")
    }

    companion object {
        fun newInstance() = TimesheetEntriesFragment()
    }
}
