package fr.o80.sample.featuredashboard.feature

import android.app.Activity
import android.content.Context
import android.content.Intent

import fr.o80.sample.featuredashboard.R
import fr.o80.sample.featuredashboard.presentation.ui.DashboardActivity
import fr.o80.sample.lib.core.Feature

/**
 * @author Olivier Perez
 */
class Dashboard : Feature {

    override val title: Int
        get() = R.string.dashboard_title

    override val icon: Int
        get() = R.drawable.ic_dashboard_menu

    override fun open(context: Context) {
        val intent = Intent(context, DashboardActivity::class.java)
        context.startActivity(intent)
    }

    override fun notYetOpened(activity: Activity): Boolean = activity !is DashboardActivity

}
