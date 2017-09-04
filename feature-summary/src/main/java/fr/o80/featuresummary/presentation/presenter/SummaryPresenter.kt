package fr.o80.featuresummary.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class SummaryPresenter @Inject constructor() : Presenter<SummaryView>() {
}