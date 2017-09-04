package fr.o80.featuresummary.presentation.ui

import android.os.Bundle
import fr.o80.featuresummary.R
import fr.o80.featuresummary.presentation.presenter.SummaryPresenter
import fr.o80.featuresummary.presentation.presenter.SummaryView
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.lib.core.ui.BaseFragment
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class SummaryFragment : BaseFragment(), SummaryView {

    @Inject
    lateinit var presenter: SummaryPresenter

    override fun showLoading() {
        TODO("not implemented")
    }

    override fun hideLoading() {
        TODO("not implemented")
    }

    override val layoutId: Int
        get() = R.layout.fragment_summary

    override fun presenter(): Presenter<PresenterView> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as SummaryActivity).component().inject(this)
    }

    companion object {
        fun newInstance() = SummaryFragment()
    }
}