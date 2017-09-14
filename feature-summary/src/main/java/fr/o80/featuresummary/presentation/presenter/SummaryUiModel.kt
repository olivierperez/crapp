package fr.o80.featuresummary.presentation.presenter

/**
 * @author Olivier Perez
 */
sealed class SummaryUiModel

object LoadingSummaryUiModel : SummaryUiModel()
class FailedSummaryUiModel(val throwable: Throwable) : SummaryUiModel()
class LoadedSummaryUiModel(val projectIds: List<Long>) : SummaryUiModel()
