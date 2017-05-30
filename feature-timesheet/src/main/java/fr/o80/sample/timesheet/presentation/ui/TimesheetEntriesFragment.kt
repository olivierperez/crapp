package fr.o80.sample.timesheet.presentation.ui

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.ui.BaseFragment
import fr.o80.sample.lib.core.ui.bindView
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesPresenter
import fr.o80.sample.timesheet.presentation.presenter.TimesheetEntriesView
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class TimesheetEntriesFragment : BaseFragment(), TimesheetEntriesView {

    @Inject
    lateinit var presenter: TimesheetEntriesPresenter

    val recyclerView: RecyclerView by bindView(R.id.recyclerView)

    val fab: FloatingActionButton by bindView(R.id.fab)

    private var adapter: TimesheetAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_timesheet_entries
    }

    override fun presenter(): Presenter<TimesheetEntriesView> {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as TimesheetActivity).component().inject(this)
    }

    override fun onResume() {
        super.onResume()

        if (adapter == null) {
            adapter = TimesheetAdapter(presenter::onTimeEntryClicked, presenter::onAddClicked)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)

            presenter.init()
        }
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
    }

    override fun showTimeEntries(entries: List<TimeEntry>, showFAB: Boolean) {
        if (showFAB) {
            fab.visibility = View.VISIBLE
            fab.setOnClickListener {presenter.onAddClicked()}
            adapter!!.setEntries(entries, showAdd = false)
        } else {
            fab.visibility = View.GONE
            adapter!!.setEntries(entries, showAdd = true)
        }
    }

    override fun showError() {
        // TODO
    }

    override fun showLoading() {
        // TODO
    }

    override fun hideLoading() {
        // TODO
    }

    companion object {

        fun newInstance(): TimesheetEntriesFragment {
            return TimesheetEntriesFragment()
        }
    }
}
