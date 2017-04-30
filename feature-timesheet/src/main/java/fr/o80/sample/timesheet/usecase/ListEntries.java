package fr.o80.sample.timesheet.usecase;

import java.util.List;

import javax.inject.Inject;

import fr.o80.sample.lib.dagger.FeatureScope;
import io.reactivex.Observable;

/**
 * @author Olivier Perez
 */
@FeatureScope
public class ListEntries {

    @Inject
    public ListEntries() {
    }

    public Observable<List<Object>> all() {
        return Observable.empty();
    }
}
