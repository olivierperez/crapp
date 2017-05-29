package fr.o80.sample.timesheet.data

import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.annotation.Migration
import com.raizlabs.android.dbflow.sql.migration.BaseMigration
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.TimeEntry

/**
 * @author Olivier Perez
 */
@FeatureScope
@Database(name = TimesheetDatabase.NAME, version = TimesheetDatabase.VERSION)
object TimesheetDatabase {
    const internal val NAME = "Timesheet"
    const internal val VERSION = 1

    @Migration(version = 0, database = TimesheetDatabase::class)
    class Initialization : BaseMigration() {
        override fun migrate(database: DatabaseWrapper?) {
            database?.let {
                TimeEntry(project = "Project", code = "CODE").save(database)
            }
        }

    }
}
