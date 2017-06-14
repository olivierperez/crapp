package fr.o80.sample.timesheet.data.entity

import android.os.Parcel
import android.os.Parcelable
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
) : BaseRXModel(), Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TimeEntry> = object : Parcelable.Creator<TimeEntry> {
            override fun createFromParcel(source: Parcel): TimeEntry = TimeEntry(source)
            override fun newArray(size: Int): Array<TimeEntry?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readParcelable<Project>(Project::class.java.classLoader),
            source.readSerializable() as Date?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeParcelable(project, flags)
        dest.writeSerializable(date)
    }
}
