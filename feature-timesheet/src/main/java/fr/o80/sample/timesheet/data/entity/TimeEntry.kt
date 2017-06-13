package fr.o80.sample.timesheet.data.entity

import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import fr.o80.sample.timesheet.data.TimesheetDatabase
import java.util.*

/**
 * @author Olivier Perez
 */
@Table(name = "TimeEntry", database = TimesheetDatabase::class)
data class TimeEntry(
        @Column(name = "id")
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,

        @ForeignKey(
                saveForeignKeyModel = false,
                stubbedRelationship = false,
                references = arrayOf(
                        ForeignKeyReference(columnName = "project", foreignKeyColumnName = "id")
                ))
        var project: Project? = null,

        @Column(name = "date")
        var date: Date? = null
) : BaseRXModel()
