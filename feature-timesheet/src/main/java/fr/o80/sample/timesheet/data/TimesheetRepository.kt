package fr.o80.sample.timesheet.data

import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.sql.language.Operator
import com.raizlabs.android.dbflow.sql.language.SQLOperator
import com.raizlabs.android.dbflow.sql.language.SQLite
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.Project
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.data.entity.TimeEntry_Table
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetRepository @Inject constructor() {

    fun all(): Single<List<TimeEntry>> {
        return RXSQLite.rx(SQLite.select().from(TimeEntry::class.java))
                .queryList()
                .subscribeOn(Schedulers.io())
    }

    fun findByProject(project: Project): TimeEntry? {
        return SQLite.select()
                .from(TimeEntry::class.java)
                .where(TimeEntry_Table.project.eq(project.id))
                .querySingle()
    }

}
