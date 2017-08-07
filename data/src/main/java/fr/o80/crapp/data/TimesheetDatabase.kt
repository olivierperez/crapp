package fr.o80.crapp.data

import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.annotation.Migration
import com.raizlabs.android.dbflow.sql.migration.BaseMigration
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper
import fr.o80.crapp.data.entity.Project
import fr.o80.sample.lib.dagger.FeatureScope

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
        override fun migrate(database: DatabaseWrapper) {
            Project(label = "FONCTIONNEMENT", code = "ZF.FPLCOS.019").save(database)
            Project(label = "FPL RUN - Mobile - LBP - Peps", code = "ZF.062275.003").save(database)
            Project(label = "FPL BUILD -Mobile-LBP portefeuille foist", code = "ZF.062275.004").save(database)
        }

    }
}
