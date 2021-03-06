package fr.o80.sample.sampleapplication

import fr.o80.featurereminder.ScheduleRemind
import fr.o80.featuresummary.Summary
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.LibConfiguration
import fr.o80.sample.lib.prefs.User
import fr.o80.sample.timesheet.Timesheet
import io.victoralbertos.rx2_permissions_result.RxPermissionsResult
import timber.log.Timber

/**
 * @author Olivier Perez
 */
class AppApplication : LibApplication() {

    override fun onCreate() {
        super.onCreate()
        RxPermissionsResult.register(this)
        Timber.plant(Timber.DebugTree())

        Timber.i("Schedule the reminder at application startup")
        ScheduleRemind(this, User(this)).scheduleReminder()
    }

    override fun buildLibConfiguration(): LibConfiguration {
        return LibConfiguration(
                home = Timesheet(),
                features = listOf(Summary())
        )
    }

}
