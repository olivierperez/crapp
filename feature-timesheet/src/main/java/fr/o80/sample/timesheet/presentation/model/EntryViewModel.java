package fr.o80.sample.timesheet.presentation.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

import fr.o80.sample.timesheet.data.entity.TimeEntry;

/**
 * @author Olivier Perez
 */
@AutoValue
public abstract class EntryViewModel {

    public static EntryViewModel inProgress() {
        return new AutoValue_EntryViewModel(true, false, null, null);
    }

    public static EntryViewModel success(List<TimeEntry> entries) {
        return new AutoValue_EntryViewModel(false, true, null, entries);
    }

    public static EntryViewModel error(Throwable throwable) {
        return new AutoValue_EntryViewModel(false, false, throwable, null);
    }

    public abstract boolean loading();

    public abstract boolean succeeded();

    @Nullable
    public abstract Throwable throwable();

    @Nullable
    public abstract List<TimeEntry> entries();
}
