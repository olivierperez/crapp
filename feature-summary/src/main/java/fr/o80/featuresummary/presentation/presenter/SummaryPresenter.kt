package fr.o80.featuresummary.presentation.presenter

import fr.o80.featuresummary.usecase.MonthSummary
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class SummaryPresenter @Inject constructor(private val monthSummary: MonthSummary) : Presenter<SummaryView>() {

    fun init() {
        addDisposable(monthSummary
                              .getMonth(Date())
                              .subscribe { m ->
                                  Timber.d("m: %s", m)
                              })
    }

}