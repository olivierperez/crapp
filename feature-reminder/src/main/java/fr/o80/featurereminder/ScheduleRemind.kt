package fr.o80.featurereminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.content.systemService
import java.util.Calendar
import android.content.Intent
import fr.o80.sample.lib.core.Const
import timber.log.Timber
import java.util.Date

/**
 * @author Olivier Perez
 */
object ScheduleRemind {

    fun scheduleReminder(context: Context) {
        val nextTrigger = Calendar.getInstance().apply {
            set(Calendar.HOUR, 18)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (nextTrigger.before(Date())) {
            nextTrigger.add(Calendar.DAY_OF_MONTH, 1)
        }

        val intent = Intent(context, RemiderReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, Const.REQUEST_CODE_REMINDER_ALARM, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        context.systemService<AlarmManager>()
                .setRepeating(AlarmManager.RTC, nextTrigger.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)

        Timber.i("Reminder scheduled")
    }

}