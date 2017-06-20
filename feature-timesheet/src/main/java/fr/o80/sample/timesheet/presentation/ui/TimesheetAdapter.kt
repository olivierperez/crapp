package fr.o80.sample.timesheet.presentation.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.ButterKnife
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.data.entity.TimeEntry

/**
 * @author Olivier Perez
 */
class TimesheetAdapter(val listener: (TimeEntry) -> Unit, val addListener: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_ADD = 0
        const val TYPE_PROJECT = 1
    }

    private var entries: List<TimeEntry>? = null

    private var showAdd: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ADD) {
            AddViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_add, parent, false))
        } else {
            EntryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_timesheet, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EntryViewHolder -> {
                val timeEntry = entries!![position]
                holder.bind(timeEntry)
            }
            is AddViewHolder -> {
                holder.listen()
            }
        }

    }

    override fun getItemCount(): Int {
        val count = if (entries != null) entries!!.size else 0
        return if (showAdd) 1 + count else count
    }

    override fun getItemViewType(position: Int): Int {
        return if (entries != null && position < entries!!.size) TYPE_PROJECT else TYPE_ADD
    }

    fun setEntries(entries: List<TimeEntry>, showAdd: Boolean) {
        this.entries = entries
        this.showAdd = showAdd
        notifyItemRangeInserted(0, entries.size)
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var projectName: TextView
        lateinit var projectCode: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(timeEntry: TimeEntry) {
            with(timeEntry) {
                projectName.text = project?.label
                projectCode.text = project?.code
            }
            itemView.setOnClickListener { listener(timeEntry) }
        }
    }

    inner class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun listen() {
            itemView.setOnClickListener { addListener() }
        }
    }
}
