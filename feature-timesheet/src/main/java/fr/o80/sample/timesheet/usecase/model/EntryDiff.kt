package fr.o80.sample.timesheet.usecase.model

import fr.o80.sample.lib.utils.GenericDiffCallback

/**
 * @author Olivier Perez
 */
class EntryDiff(o: List<EntryViewModel>, n: List<EntryViewModel>) : GenericDiffCallback<EntryViewModel>(o, n) {
    override fun isSameItem(oldItem: EntryViewModel, newItem: EntryViewModel): Boolean
            = oldItem.code == newItem.code
}