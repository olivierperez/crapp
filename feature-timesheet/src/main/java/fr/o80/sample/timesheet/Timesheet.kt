package fr.o80.sample.timesheet

import android.content.Context
import fr.o80.sample.lib.core.Feature
import fr.o80.sample.timesheet.presentation.ui.TimesheetActivity

/**
 * @author Olivier Perez
 */
class Timesheet : Feature {

    override fun open(context: Context) {
        val intent = TimesheetActivity.newIntent(context)
        context.startActivity(intent)
    }

    override val title: Int
        get() = R.string.timesheet_title

    override val icon: Int
        get() = R.drawable.ic_timesheet

}
