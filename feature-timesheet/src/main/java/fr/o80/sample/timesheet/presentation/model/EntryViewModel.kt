package fr.o80.sample.timesheet.presentation.model

import android.os.Parcelable

/**
 * @author Olivier Perez
 */
data class EntryViewModel(val label: String, val code: String, val hours: Int) : Parcelable
