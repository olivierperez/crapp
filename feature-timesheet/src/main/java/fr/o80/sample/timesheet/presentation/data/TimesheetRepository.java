package fr.o80.sample.timesheet.presentation.data;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.rx2.language.RXSQLite;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.timesheet.data.entity.TimeEntry;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Olivier Perez
 */
@FeatureScope
@Database(name = TimesheetRepository.DB_NAME, version = TimesheetRepository.DB_VERSION)
public class TimesheetRepository {

    protected static final String DB_NAME = "Timesheet";
    protected static final int DB_VERSION = 1;

    private final ModelAdapter<TimeEntry> adapter;

    @Inject
    public TimesheetRepository(ModelAdapter<TimeEntry> adapter) {
        this.adapter = adapter;
    }

    public Single<List<TimeEntry>> all() {
        return RXSQLite.rx(SQLite.select().from(TimeEntry.class))
                .queryList()
                .subscribeOn(Schedulers.io());
    }
}
