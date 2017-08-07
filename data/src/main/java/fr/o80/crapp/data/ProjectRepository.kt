package fr.o80.crapp.data

import com.raizlabs.android.dbflow.rx2.language.RXSQLite
import com.raizlabs.android.dbflow.sql.language.SQLite
import fr.o80.crapp.data.entity.Project
import fr.o80.crapp.data.entity.Project_Table
import fr.o80.sample.lib.dagger.FeatureScope
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

    fun findByCode(code: String): Project? {
        return SQLite.select()
                .from(Project::class.java)
                .where(Project_Table.code.eq(code))
                .querySingle()
    }

}
