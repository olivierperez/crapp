package fr.o80.featuresummary

import android.content.Context
import fr.o80.featuresummary.presentation.SummaryActivity
import fr.o80.sample.lib.core.Feature

/**
 * @author Olivier Perez
 */
class Summary : Feature {

    override fun open(context: Context) {
        context.startActivity(SummaryActivity.newIntent(context))
    }

    override val title: Int
        get() = R.string.summary_title

    override val icon: Int
        get() = R.drawable.ic_summary

}