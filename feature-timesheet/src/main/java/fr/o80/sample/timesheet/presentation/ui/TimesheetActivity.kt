package fr.o80.sample.timesheet.presentation.ui

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.ui.BaseDrawerActivity
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.dagger.DaggerTimesheetComponent
import fr.o80.sample.timesheet.dagger.TimesheetComponent

/**
 * @author Olivier Perez
 */
class TimesheetActivity : BaseDrawerActivity() {

    private lateinit var component: TimesheetComponent

    override val initFragment: Fragment
        get() = TimesheetEntriesFragment.newInstance()

    override val layoutId: Int
        get() =  R.layout.activity_drawer_simple

    override fun initDagger() {
        component = DaggerTimesheetComponent.builder()
                .libComponent((application as LibApplication).component())
                .build()
    }

    fun component(): TimesheetComponent {
        return component
    }

    fun createProject() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TimesheetEditFragment.create())
                .addToBackStack(null)
                .commit()
    }

    fun onNewProject() {
        fragmentManager.popBackStack()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, TimesheetActivity::class.java)
        }
    }
}
