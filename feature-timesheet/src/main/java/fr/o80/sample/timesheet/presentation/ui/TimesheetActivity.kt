package fr.o80.sample.timesheet.presentation.ui

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import fr.o80.crapp.data.entity.Project
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.ui.BaseDrawerActivity
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.dagger.DaggerTimesheetComponent
import fr.o80.sample.timesheet.dagger.TimesheetComponent

/**
 * @author Olivier Perez
 */
class TimesheetActivity : BaseDrawerActivity() {

    lateinit var component: TimesheetComponent
        private set

    override val initFragment: Fragment
        get() = TimesheetEntriesFragment.newInstance()

    override val layoutId: Int
        get() =  R.layout.activity_drawer_simple

    override fun initDagger() {
        component = DaggerTimesheetComponent.builder()
                .libComponent((application as LibApplication).component)
                .build()
    }

    fun createProject() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TimesheetEditFragment.create())
                .addToBackStack(null)
                .commit()
    }

    fun editProject(project: Project) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TimesheetEditFragment.edit(project))
                .addToBackStack(null)
                .commit()
    }

    fun onNewProject() {
        supportFragmentManager.popBackStack()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, TimesheetActivity::class.java)
        }
    }
}
