package fr.o80.sample.timesheet.usecase.model

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

/**
 * @author Olivier Perez
 */
data class EntryViewModel(val label: String, val code: String, val hours: Int, val date: Date) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            Date(parcel.readLong()))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeString(code)
        parcel.writeInt(hours)
        parcel.writeLong(date.time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntryViewModel> {
        override fun createFromParcel(parcel: Parcel): EntryViewModel {
            return EntryViewModel(parcel)
        }

        override fun newArray(size: Int): Array<EntryViewModel?> {
            return arrayOfNulls(size)
        }
    }
}
