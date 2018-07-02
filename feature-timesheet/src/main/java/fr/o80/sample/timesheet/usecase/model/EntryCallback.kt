package fr.o80.sample.timesheet.usecase.model

import android.support.v7.util.DiffUtil

/**
 * @author Olivier Perez
 */
class EntryCallback : DiffUtil.ItemCallback<EntryViewModel>() {

    override fun areItemsTheSame(oldItem: EntryViewModel, newItem: EntryViewModel): Boolean = oldItem.code == newItem.code

    override fun areContentsTheSame(oldItem: EntryViewModel, newItem: EntryViewModel): Boolean = oldItem == newItem

}