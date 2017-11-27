package fr.o80.featuresummary.usecase

import android.support.v7.util.DiffUtil
import fr.o80.featuresummary.usecase.model.ProjectSummary

/**
 * @author Olivier Perez
 */
class ProjectSummaryCallback(private val olds: List<ProjectSummary>, private val news: List<ProjectSummary>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = olds.size

    override fun getNewListSize(): Int = news.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            olds[oldItemPosition].code == news[newItemPosition].code

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            olds[oldItemPosition] === news[newItemPosition]
}