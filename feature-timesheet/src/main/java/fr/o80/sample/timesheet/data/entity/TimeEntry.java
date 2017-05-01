package fr.o80.sample.timesheet.data.entity;

import com.google.auto.value.AutoValue;

/**
 * @author Olivier Perez
 */
@AutoValue
public abstract class TimeEntry {

    public static TimeEntry create(String project, String code) {
        return new AutoValue_TimeEntry(project, code);
    }

    public abstract String project();

    public abstract String code();
}
