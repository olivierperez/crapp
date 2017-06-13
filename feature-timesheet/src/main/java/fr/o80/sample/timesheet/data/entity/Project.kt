package fr.o80.sample.timesheet.data.entity

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import fr.o80.sample.timesheet.data.TimesheetDatabase

/**
 * @author Olivier Perez
 */
@Table(name = "Project", database = TimesheetDatabase::class)
data class Project(
        @Column(name = "id")
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,

        @Column(name = "label")
        var label: String? = null,

        @Column(name = "code")
        var code: String? = null
) : BaseRXModel()
