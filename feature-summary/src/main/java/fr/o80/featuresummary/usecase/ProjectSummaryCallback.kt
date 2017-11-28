package fr.o80.featuresummary.usecase

import fr.o80.featuresummary.usecase.model.ProjectSummary
import fr.o80.sample.lib.utils.GenericDiffCallback

/**
 * @author Olivier Perez
 */
class ProjectSummaryCallback(olds: List<ProjectSummary>, news: List<ProjectSummary>) : GenericDiffCallback<ProjectSummary>(olds, news) {
    override fun isSameItem(oldItem: ProjectSummary, newItem: ProjectSummary) =
            oldItem.code == newItem.code
}