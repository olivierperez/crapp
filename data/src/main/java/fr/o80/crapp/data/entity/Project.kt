package fr.o80.crapp.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import fr.o80.crapp.data.TimesheetDatabase

/**
 * @author Olivier Perez
 */
@Table(name = "Project", database = TimesheetDatabase::class, useBooleanGetterSetters = true)
data class Project(
        @Column(name = "id")
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,

        @Column(name = "label")
        var label: String? = null,

        @Column(name = "code")
        var code: String? = null,

        @Column(name = "archived")
        var archived: Int? = 0
) : BaseRXModel(), Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Project> = object : Parcelable.Creator<Project> {
            override fun createFromParcel(source: Parcel): Project = Project(source)
            override fun newArray(size: Int): Array<Project?> = arrayOfNulls(size)
        }

    }

    constructor(source: Parcel) : this(
            id = source.readLong(),
            label = source.readString(),
            code = source.readString(),
            archived = source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(label)
        dest.writeString(code)
        dest.writeInt(archived!!)
    }
}
