package fr.o80.sample.timesheet.usecase;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.timesheet.data.entity.TimeEntry;
import io.reactivex.Observable;

/**
 * @author Olivier Perez
 */
@FeatureScope
public class ListEntries {

    @Inject
    public ListEntries() {
    }

    public Observable<List<TimeEntry>> all() {
        return Observable.just(Arrays.asList(
                TimeEntry.create("Mobile - Project 1", "AB.0123456789.02"),
                TimeEntry.create("Stagiaire", "ST.9898989898.05")));
    }
}
