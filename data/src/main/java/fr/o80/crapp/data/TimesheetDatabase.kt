package fr.o80.crapp.data

import com.raizlabs.android.dbflow.annotation.Database
import com.raizlabs.android.dbflow.annotation.Migration
import com.raizlabs.android.dbflow.kotlinextensions.update
import com.raizlabs.android.dbflow.sql.SQLiteType
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration
import com.raizlabs.android.dbflow.sql.migration.BaseMigration
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper
import fr.o80.crapp.data.entity.Project
import fr.o80.crapp.data.entity.Project_Table

/**
 * @author Olivier Perez
 */
@Database(name = "Timesheet", version = 2)
object TimesheetDatabase {

    @Migration(version = 0, database = TimesheetDatabase::class)
    class Initialization : BaseMigration() {
        override fun migrate(database: DatabaseWrapper) {
            Project(label = "FONCTIONNEMENT", code = "ZF.FPLCOS.019").save(database)
            Project(label = "FPL RUN - Mobile - LBP - Peps", code = "ZF.062275.003").save(database)
            Project(label = "FPL BUILD -Mobile-LBP portefeuille foist", code = "ZF.062275.004").save(database)
        }

    }

    @Migration(version = 2, database = TimesheetDatabase::class)
    class Version2_1 : AlterTableMigration<Project>(Project::class.java) {

        override fun onPreMigrate() {
            super.onPreMigrate()
            addColumn(SQLiteType.INTEGER, "archived")
        }

    }

    @Migration(version = 2, database = TimesheetDatabase::class)
    class Version2_2 : BaseMigration() {

        override fun migrate(database: DatabaseWrapper) {
            update<Project>()
                    .set(Project_Table.archived.eq(0))
                    .execute(database)
        }

    }
}
