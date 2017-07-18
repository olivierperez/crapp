package fr.o80.sample.featuredashboard.presentation.presenter

import fr.o80.sample.featuredashboard.DashboardService
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class DashboardPresenter @Inject constructor() : Presenter<DashboardView>() {

    @Inject
    lateinit var dashboardService: DashboardService
}