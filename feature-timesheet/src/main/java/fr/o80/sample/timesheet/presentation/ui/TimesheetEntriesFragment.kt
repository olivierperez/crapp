package fr.o80.sample.timesheet.presentation.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.tinsuke.icekick.extension.state
import fr.o80.sample.lib.core.ui.BaseFragment
import fr.o80.sample.lib.core.ui.bindView
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.model.*
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesPresenter
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesView
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEntriesFragment : BaseFragment(), TimesheetEntriesView {

    @Inject
    lateinit var presenter: TimesheetEntriesPresenter

    private var viewModel: EntriesViewModel? by state(EntryViewModelBundler())

    val recyclerView: RecyclerView by bindView(R.id.recyclerView)

    val fab: FloatingActionButton by bindView(R.id.fab)

    private var adapter: TimesheetAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_timesheet_entries
    }

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
            adapter = TimesheetAdapter(presenter::onTimeEntryClicked, presenter::onAddClicked)
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
                    Timber.d("Loaded, showFAB=%s, %s", viewModel.showFAB, viewModel.entries)
                    hideLoading()
                    showTimeEntries(viewModel.entries, !viewModel.showFAB)
                    showFAB(viewModel.showFAB)
                }

                is FailedEntriesViewModel -> {
                    Timber.e(viewModel.throwable, "Cannot load time entries")
                    hideLoading()
                    showError()
                }
            }
        }
    }

    fun showTimeEntries(entries: List<TimeEntry>, showAddEntry: Boolean) {
        adapter!!.setEntries(entries, showAddEntry)
    }

    fun showFAB(visible: Boolean) {
        fab.visibility = if (visible) View.VISIBLE else View.GONE
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
