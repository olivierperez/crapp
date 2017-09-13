package fr.o80.featuresummary.presentation.presenter

import fr.o80.featuresummary.usecase.model.ProjectSummary

/**
 * @author Olivier Perez
 */
sealed class SummaryUiModel

object LoadingSummaryUiModel : SummaryUiModel()
class FailedSummaryUiModel(val throwable: Throwable) : SummaryUiModel()
class LoadedSummaryUiModel(val summaries: List<ProjectSummary>) : SummaryUiModel()
