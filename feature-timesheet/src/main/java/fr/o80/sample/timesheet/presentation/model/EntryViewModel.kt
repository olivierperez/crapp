package fr.o80.sample.timesheet.presentation.model

import fr.o80.sample.timesheet.data.entity.TimeEntry

/**
 * @author Olivier Perez
 */
sealed class EntryViewModel
object LoadingEntryViewModel : EntryViewModel()
data class LoadedEntryViewModel(val entries: List<TimeEntry>) : EntryViewModel()
data class FailedEntryViewModel(val throwable: Throwable) : EntryViewModel()
