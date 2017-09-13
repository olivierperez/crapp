package fr.o80.featuresummary.presentation.presenter

import fr.o80.sample.lib.core.presenter.PresenterView

/**
 * @author Olivier Perez
 */
interface SummaryView : PresenterView {
    fun update(uiModel: SummaryUiModel)
}