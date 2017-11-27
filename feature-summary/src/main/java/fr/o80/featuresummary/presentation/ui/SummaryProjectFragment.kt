package fr.o80.featuresummary.presentation.ui

import android.os.Bundle
import android.view.View
import fr.o80.featuresummary.R
import fr.o80.featuresummary.presentation.presenter.SummaryProjectPresenter
import fr.o80.featuresummary.presentation.presenter.SummaryProjectView
import fr.o80.sample.lib.core.ui.BaseFragment
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class SummaryProjectFragment : BaseFragment(), SummaryProjectView {

    @Inject
    lateinit var presenter: SummaryProjectPresenter

    override fun showLoading() {
        TODO("not implemented")
    }

    override fun hideLoading() {
        TODO("not implemented")
    }

    override val layoutId: Int
        get() = R.layout.fragment_summary_project

    override fun presenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as SummaryActivity).component().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
    }

    companion object {
        private const val EXTRA_PROJECT_ID = "EXTRA_PROJECT_ID"

        fun newInstance(projectId: Long): SummaryProjectFragment {
            return SummaryProjectFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_PROJECT_ID, projectId)
                }
            }
        }
    }

}
