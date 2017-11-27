package fr.o80.featuresummary.presentation.ui

import android.os.Bundle
import android.view.View
import fr.o80.featuresummary.R
import fr.o80.featuresummary.presentation.presenter.LoadedSummaryUiModel
import fr.o80.featuresummary.presentation.presenter.SummaryPresenter
import fr.o80.featuresummary.presentation.presenter.SummaryUiModel
import fr.o80.featuresummary.presentation.presenter.SummaryView
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.lib.core.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_summary.*
import timber.log.Timber
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
    }

    override fun update(uiModel: SummaryUiModel) {
        Timber.d("UiModel: %s", uiModel)
        when(uiModel) {
            is LoadedSummaryUiModel ->
                viewPager.adapter = SummaryPagerAdapter(childFragmentManager, uiModel.projectIds)
        }
    }

    companion object {
        fun newInstance() = SummaryFragment()
    }
}