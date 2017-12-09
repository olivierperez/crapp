package fr.o80.featuresummary.presentation.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.o80.featuresummary.R
import fr.o80.featuresummary.usecase.ProjectSummaryCallback
import fr.o80.featuresummary.usecase.model.ProjectSummary

/**
 * @author Olivier Perez
 */
class SummaryProjectAdapter : RecyclerView.Adapter<SummaryProjectAdapter.SummaryProjectViewHolder>() {

    private var entries: List<ProjectSummary> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryProjectViewHolder =
            SummaryProjectViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false))

    override fun onBindViewHolder(holder: SummaryProjectViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount(): Int = entries.size

    fun update(entries: List<ProjectSummary>) {
        val diffResult = DiffUtil.calculateDiff(ProjectSummaryCallback(this.entries, entries))
        this.entries = entries
        diffResult.dispatchUpdatesTo(this)
    }

    inner class SummaryProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val projectName: TextView = itemView.findViewById(R.id.project_name)
        private val projectCode: TextView = itemView.findViewById(R.id.project_code)
        private val time: TextView = itemView.findViewById(R.id.time)

        fun bind(projectSummary: ProjectSummary) {
            projectName.text = projectSummary.label
            projectCode.text = projectSummary.code

            when {
                projectSummary.time < 8 -> {
                    time.text = "${projectSummary.time}h"
                }
                projectSummary.time % 8 == 0 -> {
                    time.text = "${projectSummary.time/8}d"
                }
                else -> {
                    time.text = "${projectSummary.time/8}d ${projectSummary.time - (projectSummary.time/8) * 8}h"
                }
            }
        }

    }
}