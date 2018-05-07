package fr.o80.sample.sampleapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.o80.featurereminder.ScheduleRemind
import timber.log.Timber

class OnStartupReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Timber.i("Schedule the reminder at phone boot")
        ScheduleRemind.scheduleReminder(context)
    }

}
