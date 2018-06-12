package fr.o80.featuresummary.presentation.presenter

import android.support.annotation.StringRes
import fr.o80.sample.lib.core.presenter.PresenterView

/**
 * @author Olivier Perez
 */
interface SummaryView : PresenterView {
    fun showLoading()
    fun hideLoading()
    fun update(uiModel: LoadedSummaryUiModel)
    fun showError(@StringRes stringRes: Int)
    fun showSendOption()
    fun send(email: String, title: String, body: String)
    fun showEmailPopup()
}