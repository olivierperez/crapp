package fr.o80.sample.timesheet.data

import com.raizlabs.android.dbflow.annotation.Database

import fr.o80.sample.lib.dagger.FeatureScope

/**
 * @author Olivier Perez
 */
@FeatureScope
@Database(name = TimesheetDatabase.NAME, version = TimesheetDatabase.VERSION)
object TimesheetDatabase {
    const internal val NAME = "Timesheet"
    const internal val VERSION = 1
}
