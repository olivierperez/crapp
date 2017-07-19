package fr.o80.sample.timesheet.data

import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.sql.language.SQLite
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.Project
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class ProjectRepository @Inject constructor() {

    fun all(): Single<List<Project>> {
        return RXSQLite.rx(SQLite.select().from(Project::class.java))
                .queryList()
                .subscribeOn(Schedulers.io())
    }

}
