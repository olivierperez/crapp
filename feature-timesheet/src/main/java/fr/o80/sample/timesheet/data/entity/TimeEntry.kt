package fr.o80.sample.timesheet.data.entity

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import fr.o80.sample.timesheet.data.TimesheetDatabase

/**
 * @author Olivier Perez
 */
@Table(name = "TimeEntry", database = TimesheetDatabase::class)
class TimeEntry(
        @PrimaryKey(autoincrement = true) @Column(name = "id") var id: Long = 0,
        @Column(name = "project") var project: String? = null,
        @Column(name = "code") var code: String? = null)
    : BaseModel()
