package fr.o80.sample.timesheet.data

import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.sql.language.SQLite
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.Project
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.data.entity.TimeEntry_Table
import fr.o80.sample.timesheet.util.plus
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.Date
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

    fun findByProjectAndDate(project: Project, date: Date): TimeEntry? {
        return SQLite.select()
                .from(TimeEntry::class.java)
                .where(TimeEntry_Table.project.eq(project.id))
                .and(TimeEntry_Table.date.greaterThanOrEq(date))
                .and(TimeEntry_Table.date.lessThan(date + 1))
                .querySingle()
    }

    fun findByDateRange(after: Date, strictlyBefore: Date): Single<List<TimeEntry>> {
        return RXSQLite.rx(SQLite.select().from(TimeEntry::class.java)
                                   .where(TimeEntry_Table.date.greaterThanOrEq(after))
                                   .and(TimeEntry_Table.date.lessThan(strictlyBefore)))
                .queryList()
                .subscribeOn(Schedulers.io())
    }

}
