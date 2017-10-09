package fr.o80.sample.timesheet.presentation.model

import fr.o80.sample.timesheet.usecase.model.EntryViewModel
import java.util.Date

/**
 * @author Olivier Perez
 */
sealed class EntriesViewModel

object LoadingEntriesViewModel : EntriesViewModel()
data class LoadedEntriesViewModel(val entries: List<EntryViewModel>, val totalHours: Int, val date: Date) : EntriesViewModel()
data class FailedEntriesViewModel(val throwable: Throwable) : EntriesViewModel()

