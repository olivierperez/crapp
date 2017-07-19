package fr.o80.sample.timesheet.presentation.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.tinsuke.icekick.extension.state
import fr.o80.sample.lib.core.ui.BaseFragment
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.model.*
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesPresenter
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesView
import kotlinx.android.synthetic.main.fragment_timesheet_entries.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEntriesFragment : BaseFragment(), TimesheetEntriesView {

    @Inject
    lateinit var presenter: TimesheetEntriesPresenter

    private var viewModel: EntriesViewModel? by state(EntryViewModelBundler())

    private var adapter: TimesheetAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_timesheet_entries

    override fun presenter(): TimesheetEntriesPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as TimesheetActivity).component().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as AppCompatActivity
        val toolbar = view.findViewById(R.id.toolbar) as Toolbar

        activity.setSupportActionBar(toolbar)

        val actionBar = activity.supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        fab.setOnClickListener { presenter.onAddClicked() }
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Load or restore state
        if (viewModel == null) {
            adapter = TimesheetAdapter(presenter::onTimeEntryClicked)
            recyclerView.adapter = adapter

            presenter.init()
        } else {
            update(viewModel)
        }
    }

    override fun update(viewModel: EntriesViewModel?) {
        this.viewModel = viewModel

        with(viewModel) {
            when (viewModel) {
                is LoadingEntriesViewModel -> showLoading()

                is LoadedEntriesViewModel -> {
                    Timber.d("Loaded, %s", viewModel.entries)
                    hideLoading()
                    showTimeEntries(viewModel.entries)
                }

                is FailedEntriesViewModel -> {
                    Timber.e(viewModel.throwable, "Cannot load time entries")
                    hideLoading()
                    showError()
                }
            }
        }
    }

    fun showTimeEntries(entries: List<TimeEntry>) {
        adapter!!.setEntries(entries)
    }

    override fun showError() {
        //TODO("not implemented")
    }

    override fun goToCreateProject() {
        (activity as TimesheetActivity).createProject()
    }

    override fun showLoading() {
        //TODO("not implemented")
    }

    override fun hideLoading() {
        //TODO("not implemented")
    }

    companion object {

        fun newInstance(): TimesheetEntriesFragment {
            return TimesheetEntriesFragment()
        }
    }
}
