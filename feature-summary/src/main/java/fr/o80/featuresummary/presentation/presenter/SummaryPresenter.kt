package fr.o80.featuresummary.presentation.presenter

import fr.o80.featuresummary.R
import fr.o80.featuresummary.usecase.MonthSummary
import fr.o80.sample.lib.prefs.User
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.html.body
import kotlinx.html.html
import kotlinx.html.li
import kotlinx.html.stream.appendHTML
import kotlinx.html.ul
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class SummaryPresenter @Inject constructor(private val monthSummary: MonthSummary, private val user: User) : Presenter<SummaryView>() {

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
        if (data != null) {
            if (user.rememberEmail) {
                sendSummaryMail(user.email)
            } else {
                view.showEmailPopup()
            }
        }
    }

    fun onEmailChoosen(email: String, rememberEmail: Boolean) {
        Timber.i("Email choosen")

        // Remember email if user
        if (rememberEmail) {
            user.rememberEmail = true
            user.email = email
        } else {
            user.rememberEmail = false
            user.email = ""
        }

        sendSummaryMail(email)
    }

    private fun sendSummaryMail(email: String) {
        data?.let { uiModel ->
            val body = StringBuilder().appendHTML().html {
                body {
                    ul {
                        uiModel.summary.forEach { project ->
                            li { +"${project.label} | ${project.code} | ${project.time}h" }
                        }
                    }
                }
            }.toString()

            view.send(email, "CRApp summary", body)
        }
    }

}
