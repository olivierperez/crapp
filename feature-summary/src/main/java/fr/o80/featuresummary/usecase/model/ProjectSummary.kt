package fr.o80.featuresummary.usecase.model

/**
 * @author Olivier Perez
 */
data class ProjectSummary(val label: String, val code: String, val summaryTimeEntries: List<SummaryTimeEntry>) {
    val time : Int = summaryTimeEntries.foldRight(0) { summaryTimeEntry, acc: Int -> acc + summaryTimeEntry.hours }
}