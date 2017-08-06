package fr.o80.sample.timesheet.presentation.model

import android.os.Bundle
import com.tinsuke.icekick.bundler.Bundler
import fr.o80.sample.timesheet.Const.KEY_ENTRIES
import fr.o80.sample.timesheet.Const.KEY_TOTALHOURS
import fr.o80.sample.timesheet.Const.KEY_DATE
import fr.o80.sample.timesheet.usecase.model.EntryViewModel
import java.util.*

/**
 * @author Olivier Perez
 */
sealed class EntriesViewModel

object LoadingEntriesViewModel : EntriesViewModel()
data class LoadedEntriesViewModel(val entries: List<EntryViewModel>, val totalHours: Int, val date: Date) : EntriesViewModel()
data class FailedEntriesViewModel(val throwable: Throwable) : EntriesViewModel()

class EntryViewModelBundler : Bundler<EntriesViewModel> {

    override fun load(bundle: Bundle, key: String): EntriesViewModel? {
        return if (bundle.containsKey(KEY_ENTRIES)) {

            LoadedEntriesViewModel(
                    bundle.getParcelableArrayList(KEY_ENTRIES),
                    bundle.getInt(KEY_TOTALHOURS),
                    Date(bundle.getLong(KEY_DATE))
            )
        } else {
            null
        }
    }

    override fun save(bundle: Bundle, key: String, value: EntriesViewModel?) {
        when (value) {
            is LoadedEntriesViewModel -> {
                bundle.putParcelableArrayList(KEY_ENTRIES, value.entries)
                bundle.putInt(KEY_TOTALHOURS, value.totalHours)
                bundle.putLong(KEY_DATE, value.date.time)
            }
        }
    }

}

private fun Bundle.putParcelableArrayList(key: String, entries: List<EntryViewModel>) {
    when (entries) {
        is ArrayList -> putParcelableArrayList(key, entries)
        else -> putParcelableArrayList(key, ArrayList(entries))
    }
}
