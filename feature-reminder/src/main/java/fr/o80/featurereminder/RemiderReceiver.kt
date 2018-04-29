package fr.o80.featurereminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.o80.featurereminder.dagger.DaggerReminderComponent
import fr.o80.featurereminder.usecase.TotalPerDay
import fr.o80.sample.lib.core.Const.HOURS_PER_DAY
import fr.o80.sample.lib.core.LibApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
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
                .libComponent((context.applicationContext as LibApplication).component)
                .build()
                .inject(this)
    }

    override fun onReceive(context: Context, intent: Intent) {
        inject(context)
        Timber.d("Reminder triggered")
        totalPerDay.getTodayHours()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { totalHoursForToday ->
                            Timber.d("Hours logged today: $totalHoursForToday / $HOURS_PER_DAY")
                            if (totalHoursForToday < HOURS_PER_DAY) {
                                displayNotification(HOURS_PER_DAY, totalHoursForToday)
                            }
                        },
                        onError = {
                            Timber.e(it, "Failed to get the logged hours of today")
                        })
    }

    private fun displayNotification(hoursRequiredPerDay: Int, totalHoursForToday: Int) {
        TODO("Show notification")
    }

}
