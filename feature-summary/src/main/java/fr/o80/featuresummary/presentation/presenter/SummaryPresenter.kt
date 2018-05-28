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

    private var data: LoadedSummaryUiModel? = null

    fun init() {
        addDisposable(monthSummary
                              .getMonth(Date())

                              .toObservable()
                              .map<SummaryUiModel> { LoadedSummaryUiModel(it) }
                              .onErrorReturn { FailedSummaryUiModel(it) }
                              .startWith(LoadingSummaryUiModel)

                              .doOnNext {
                                  if (it is LoadedSummaryUiModel)
                                      data = it
                              }

                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribeBy(
                                      onNext = { uiModel ->
                                          when (uiModel) {
                                              is LoadingSummaryUiModel -> {
                                                  view.showLoading()
                                              }
                                              is LoadedSummaryUiModel -> {
                                                  Timber.i("Summary loaded")
                                                  view.hideLoading()
                                                  view.update(uiModel)
                                                  if (uiModel.summary.isNotEmpty()) {
                                                      view.showSendOption()
                                                  }
                                              }
                                              is FailedSummaryUiModel -> {
                                                  view.showError(R.string.summary_failed_to_load)
                                              }
                                          }
                                      })
                     )
    }

    fun onSendClicked() {
        Timber.i("Send option clicked")
    }

}
