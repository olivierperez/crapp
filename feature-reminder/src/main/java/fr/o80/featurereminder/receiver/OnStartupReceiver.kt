package fr.o80.featurereminder.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import fr.o80.featurereminder.ScheduleRemind
import fr.o80.featurereminder.dagger.DaggerReminderComponent
import timber.log.Timber
import javax.inject.Inject

class OnStartupReceiver : BroadcastReceiver() {

    @Inject
    lateinit var scheduler: ScheduleRemind

    override fun onReceive(context: Context, intent: Intent?) {
        Timber.i("Schedule the reminder at phone boot")
        DaggerReminderComponent.builder()
                .build()
                .inject(this)
        scheduler.scheduleReminder()
    }

}
