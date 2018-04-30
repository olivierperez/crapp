package fr.o80.featurereminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import fr.o80.featurereminder.dagger.DaggerReminderComponent
import fr.o80.featurereminder.usecase.TotalPerDay
import fr.o80.sample.lib.core.Const.HOURS_PER_DAY
import fr.o80.sample.lib.core.LibApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import android.app.NotificationChannel
import android.app.NotificationManager

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
                                displayNotification(context, HOURS_PER_DAY, totalHoursForToday)
                            }
                        },
                        onError = {
                            Timber.e(it, "Failed to get the logged hours of today")
                        })
    }

    private fun displayNotification(context: Context, hoursRequiredPerDay: Int, totalHoursForToday: Int) {
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_title)
            val desc = context.getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = desc
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
                enableLights(false)
                enableVibration(false)
                setBypassDnd(false)
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }

        val mainMessage = context.getString(if (totalHoursForToday == 0) R.string.reminder_you_have_logged_anything
                                            else R.string.reminder_complete_your_logged_time)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_notif_reminder)
            setContentTitle(mainMessage)
            if (totalHoursForToday > 0) {setContentText(context.resources.getQuantityString(R.plurals.reminder_x_hours_on_x_required, totalHoursForToday, totalHoursForToday, hoursRequiredPerDay))}
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        notificationManager.notify(5, builder.build())

    }

    companion object {
        const val CHANNEL_ID = "REMINDER"
    }

}
