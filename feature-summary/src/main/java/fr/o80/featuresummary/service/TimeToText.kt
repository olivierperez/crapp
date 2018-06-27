package fr.o80.featuresummary.service

import android.content.Context
import fr.o80.featuresummary.R
import fr.o80.sample.lib.dagger.FeatureScope
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimeToText
@Inject constructor(private val context: Context) {

    fun convert(time: Int): String {
        return when {
            time < 8 -> context.getString(R.string.summary_time_hours, time)
            time % 8 == 0 -> context.getString(R.string.summary_time_days, time / 8)
            else -> context.getString(R.string.summary_time_days_hours, time / 8, time - (time / 8) * 8)
        }
    }

}