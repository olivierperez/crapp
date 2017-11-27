package fr.o80.featuresummary.presentation.presenter

import fr.o80.featuresummary.R
import fr.o80.featuresummary.usecase.MonthSummary
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
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

                              .toObservable()
                              .map<SummaryUiModel> { LoadedSummaryUiModel(it) }
                              .onErrorReturn { FailedSummaryUiModel(it) }
                              .startWith(LoadingSummaryUiModel)

                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribeBy(
                                      onNext = {
                                          when (it) {
                                              is LoadingSummaryUiModel -> {
                                                  view.showLoading()
                                              }
                                              is LoadedSummaryUiModel -> {
                                                  Timber.i("Summary loaded")
                                                  view.hideLoading()
                                                  view.update(it)
                                              }
                                              is FailedSummaryUiModel -> {
                                                  view.showError(R.string.summary_failed_to_load)
                                              }
                                          }
                                      })
                     )
    }

}
