package fr.o80.featuresummary.usecase.model

/**
 * @author Olivier Perez
 */
data class ProjectSummary(val label: String, val code: String, val summaryTimeEntries: List<SummaryTimeEntry>)