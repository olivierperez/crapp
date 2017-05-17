package fr.o80.sample.timesheet.data.entity;

import com.google.auto.value.AutoValue;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.UUID;

/**
 * @author Olivier Perez
 */
@Table(database = TimeEntry.class)
public class TimeEntry {

    @PrimaryKey
    private UUID id;

    @Column(name = "project")
    private String project;

    @Column(name = "code")
    private String code;

    public TimeEntry(String project, String code) {
        this.project = project;
        this.code = code;
    }

    public TimeEntry() {
    }
}
