package fr.o80.featuresummary

import android.app.Activity
import android.content.Context
import fr.o80.featuresummary.presentation.ui.SummaryActivity
import fr.o80.sample.lib.core.Feature

/**
 * @author Olivier Perez
 */
class Summary : Feature {

    override val title: Int
        get() = R.string.summary_title

    override val icon: Int
        get() = R.drawable.ic_summary

    override fun open(context: Context) {
        context.startActivity(SummaryActivity.newIntent(context))
    }

    override fun notYetOpened(activity: Activity): Boolean = activity !is SummaryActivity

}
