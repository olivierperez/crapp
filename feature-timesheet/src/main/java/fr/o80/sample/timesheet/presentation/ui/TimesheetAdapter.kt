package fr.o80.sample.timesheet.presentation.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.usecase.model.EntryCallback
import fr.o80.sample.timesheet.usecase.model.EntryViewModel

/**
 * @author Olivier Perez
 */
class TimesheetAdapter(
        val onClick: (EntryViewModel) -> Unit,
        val onTimeAdded: (EntryViewModel) -> Unit,
        val onLongTimeAdded: (EntryViewModel) -> Boolean,
        val onTimeRemoved: (EntryViewModel) -> Unit,
        val onLongTimeRemoved: (EntryViewModel) -> Boolean
) : ListAdapter<EntryViewModel, TimesheetAdapter.EntryViewHolder>(EntryCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder =
            EntryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_timesheet, parent, false))

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val projectName: TextView = itemView.findViewById(R.id.projectName)
        private val projectCode: TextView = itemView.findViewById(R.id.projectCode)
        private val time: TextView = itemView.findViewById(R.id.time)
        private val addTime: TextView = itemView.findViewById(R.id.plusButton)
        private val removeTime: TextView = itemView.findViewById(R.id.minusButton)

        fun bind(timeEntry: EntryViewModel) {
            with(timeEntry) {
                projectName.text = label
                projectCode.text = code
                time.text = itemView.context.getString(R.string.hours, hours)
            }
            itemView.setOnClickListener { onClick(timeEntry) }
            addTime.setOnClickListener { onTimeAdded(timeEntry) }
            addTime.setOnLongClickListener { onLongTimeAdded(timeEntry) }
            removeTime.setOnClickListener { onTimeRemoved(timeEntry) }
            removeTime.setOnLongClickListener { onLongTimeRemoved(timeEntry) }
        }
    }
}
