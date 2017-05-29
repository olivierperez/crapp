package fr.o80.sample.timesheet.presentation.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.o80.sample.lib.core.ui.bindView
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.R2
import fr.o80.sample.timesheet.data.entity.TimeEntry

/**
 * @author Olivier Perez
 */
class TimesheetAdapter : RecyclerView.Adapter<TimesheetAdapter.EntryViewHolder>() {

    private var items: List<TimeEntry>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timesheet, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(items!![position])
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    fun setItems(entries: List<TimeEntry>) {
        items = entries
        notifyItemRangeInserted(0, entries.size)
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val projectName: TextView by bindView(R2.id.project_name)
        val projectCode: TextView by bindView(R2.id.project_code)

        fun bind(timeEntry: TimeEntry) {
            with (timeEntry) {
                projectName.text = project
                projectCode.text = code
            }
        }
    }
}
