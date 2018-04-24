package fr.o80.featurereminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.o80.featurereminder.dagger.DaggerReminderComponent
import fr.o80.featurereminder.usecase.TotalPerDay
import fr.o80.sample.lib.core.Const.HOURS_PER_DAY
import fr.o80.sample.lib.core.LibApplication
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class RemiderReceiver : BroadcastReceiver() {

    @Inject
    lateinit var totalPerDay: TotalPerDay

    private fun inject(context: Context) {
        DaggerReminderComponent.builder()
                .libComponent((context.applicationContext as LibApplication).component())
                .build()
    }

    override fun onReceive(context: Context, intent: Intent) {
        inject(context)
        Timber.d("Reminder triggered")
        val totalHoursForToday = totalPerDay.today()
        if (totalHoursForToday < HOURS_PER_DAY) {
            displayNotification(HOURS_PER_DAY, totalHoursForToday)
        }
    }

    private fun displayNotification(hoursRequiredPerDay: Int, totalHoursForToday: Int) {
        TODO("Show notification")
    }

}
