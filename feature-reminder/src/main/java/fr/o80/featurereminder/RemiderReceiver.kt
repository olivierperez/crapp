package fr.o80.featurereminder

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import fr.o80.featurereminder.dagger.DaggerReminderComponent
import fr.o80.featurereminder.usecase.DayChecker
import fr.o80.featurereminder.usecase.TotalPerDay
import fr.o80.sample.lib.core.Const
import fr.o80.sample.lib.core.Const.HOURS_PER_DAY
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.ui.MainActivity
import fr.o80.sample.lib.utils.today
import fr.o80.sample.lib.utils.todayCalendar
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

    @Inject
    lateinit var dayChecker: DayChecker

    private fun inject(context: Context) {
        DaggerReminderComponent.builder()
                .libComponent((context.applicationContext as LibApplication).component)
                .build()
                .inject(this)
    }

    override fun onReceive(context: Context, intent: Intent) {
        inject(context)
        Timber.d("Reminder triggered")

        if (dayChecker shouldNotifyFor todayCalendar()) {
            totalPerDay.getTodayHours()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = { totalHoursForToday ->
                                Timber.d("Hours logged today: $totalHoursForToday / $HOURS_PER_DAY")
                                if (totalHoursForToday < HOURS_PER_DAY) {
                                    displayNotification(context, HOURS_PER_DAY, totalHoursForToday)
                                }
                            },
                            onError = {
                                Timber.e(it, "Failed to get the logged hours of today")
                            })
        }
    }

    private fun displayNotification(context: Context, hoursRequiredPerDay: Int, totalHoursForToday: Int) {
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notification(context, notificationManager) {
            channel(CHANNEL_ID) {
                name = context.getString(R.string.notification_channel_title)
                description = context.getString(R.string.notification_channel_description)
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
                importance = NotificationManager.IMPORTANCE_LOW
                lights = false
                vibration = false
                bypassDnd = false
            }

            intent(context, MainActivity::class.java, Const.REQUEST_CODE_REMINDER_NOTIFICATION, PendingIntent.FLAG_UPDATE_CURRENT)

            id = today().time.toInt()
            smallIcon = R.drawable.ic_notif_reminder
            autoCancel = true
            priority = NotificationCompat.PRIORITY_DEFAULT

            if (totalHoursForToday > 0) {
                title = context.getString(R.string.reminder_complete_your_logged_time)
                text = context.resources.getQuantityString(R.plurals.reminder_x_hours_on_x_required, totalHoursForToday, totalHoursForToday, hoursRequiredPerDay)
            } else {
                title = context.getString(R.string.reminder_you_have_logged_anything)
            }
        }


    }

    companion object {
        const val CHANNEL_ID = "REMINDER"
    }

}
