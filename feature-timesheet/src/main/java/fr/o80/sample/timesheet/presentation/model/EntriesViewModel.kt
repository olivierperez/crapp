package fr.o80.sample.timesheet.presentation.model

import fr.o80.sample.timesheet.usecase.model.EntryViewModel
import kotlinx.serialization.Serializable
import java.util.Date

/**
 * @author Olivier Perez
 */
sealed class EntriesViewModel

object LoadingEntriesViewModel : EntriesViewModel()
data class FailedEntriesViewModel(val throwable: Throwable) : EntriesViewModel()

@Serializable
data class LoadedEntriesViewModel(val entries: List<EntryViewModel>, val totalHours: Int, val date: Date) : EntriesViewModel()

