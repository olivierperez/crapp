package fr.o80.sample.featuredashboard.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView

import javax.inject.Inject

import fr.o80.sample.featuredashboard.DashboardService
import fr.o80.sample.featuredashboard.R
import fr.o80.sample.featuredashboard.presentation.presenter.DashboardPresenter
import fr.o80.sample.featuredashboard.presentation.presenter.DashboardView
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.ui.BaseFragment

/**
 * @author Olivier Perez
 */
class DashboardFragment : BaseFragment(), DashboardView {

    @Inject
    lateinit var presenter: DashboardPresenter

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun onResume() {
        super.onResume()
        (activity as DashboardActivity).component().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.findViewById(R.id.textView) as TextView).setText(R.string.lorem_ipsum)
    }

    override fun presenter(): DashboardPresenter {
        return presenter
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
