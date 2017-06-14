package fr.o80.sample.timesheet.presentation.model

import android.os.Bundle
import com.tinsuke.icekick.bundler.Bundler
import fr.o80.sample.timesheet.data.entity.TimeEntry
import fr.o80.sample.timesheet.Const.KEY_ENTRIES
import fr.o80.sample.timesheet.Const.KEY_SHOW_FAB

/**
 * @author Olivier Perez
 */
sealed class EntriesViewModel

object LoadingEntriesViewModel : EntriesViewModel()
data class LoadedEntriesViewModel(val entries: List<TimeEntry>, val showFAB : Boolean) : EntriesViewModel()
data class FailedEntriesViewModel(val throwable: Throwable) : EntriesViewModel()

class EntryViewModelBundler : Bundler<EntriesViewModel> {

    override fun load(bundle: Bundle, key: String): EntriesViewModel? {
        return if (bundle.containsKey(KEY_ENTRIES)) {
            LoadedEntriesViewModel(
                    bundle.getParcelableArrayList(KEY_ENTRIES),
                    bundle.getBoolean(KEY_SHOW_FAB)
            )
        } else {
            null
        }
    }

    override fun save(bundle: Bundle, key: String, value: EntriesViewModel?) {
        when (value) {
            is LoadedEntriesViewModel -> {
                bundle.putParcelableArrayList(KEY_ENTRIES, value.entries)
                bundle.putBoolean(KEY_SHOW_FAB, value.showFAB)
            }
        }
    }

}

private fun Bundle.putParcelableArrayList(key: String, entries: List<TimeEntry>) {
    when (entries) {
        is ArrayList -> putParcelableArrayList(key, entries)
        else -> putParcelableArrayList(key, ArrayList(entries))
    }
}
