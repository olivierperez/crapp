package fr.o80.featuresummary.presentation.presenter

import fr.o80.featuresummary.usecase.MonthSummary
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import io.reactivex.android.schedulers.AndroidSchedulers
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
                              .getProjectsOnMonth(Date())

                              .toObservable()
                              .map<SummaryUiModel> { LoadedSummaryUiModel(it) }
                              .onErrorReturn { FailedSummaryUiModel(it) }
                              .startWith(LoadingSummaryUiModel)

                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribe(view::update))
    }

}