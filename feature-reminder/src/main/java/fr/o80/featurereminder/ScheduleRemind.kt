package fr.o80.featurereminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.content.systemService
import java.util.Calendar
import android.content.Intent
import fr.o80.featurereminder.receiver.RemiderReceiver
import fr.o80.sample.lib.core.Const
import fr.o80.sample.lib.prefs.User
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class ScheduleRemind
@Inject constructor(private val context: Context, private val user: User){

    fun scheduleReminder() {
        val nextTrigger = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, user.reminderHour)
            set(Calendar.MINUTE, user.reminderMinute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        val intent = Intent(context, RemiderReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, Const.REQUEST_CODE_REMINDER_ALARM, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        context.systemService<AlarmManager>()
                .setRepeating(AlarmManager.RTC, nextTrigger.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)

        Timber.i("Reminder scheduled, next execution is %s", nextTrigger)
    }

}