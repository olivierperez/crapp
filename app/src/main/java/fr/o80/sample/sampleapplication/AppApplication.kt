package fr.o80.sample.sampleapplication

import fr.o80.sample.featuredashboard.feature.Dashboard
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.LibConfiguration
import fr.o80.sample.timesheet.Timesheet
import io.victoralbertos.rx2_permissions_result.RxPermissionsResult

/**
 * @author Olivier Perez
 */
class AppApplication : LibApplication() {

    override fun onCreate() {
        super.onCreate()
        RxPermissionsResult.register(this)
    }

    override fun buildLibConfiguration(): LibConfiguration {
        return LibConfiguration.Builder()
                .homeFeature(Timesheet())
                .addFeature(Dashboard())
                .build()
    }

}
