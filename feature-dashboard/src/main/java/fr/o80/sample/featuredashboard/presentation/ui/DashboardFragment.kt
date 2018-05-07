package fr.o80.sample.featuredashboard.presentation.ui

import android.os.Bundle
import fr.o80.sample.featuredashboard.R
import fr.o80.sample.featuredashboard.presentation.presenter.DashboardPresenter
import fr.o80.sample.featuredashboard.presentation.presenter.DashboardView
import fr.o80.sample.lib.core.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class DashboardFragment : BaseFragment(), DashboardView {

    @Inject
    lateinit var presenter: DashboardPresenter

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun presenter(): DashboardPresenter {
        return presenter
    }

    override fun inject() {
        (activity as DashboardActivity).component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.setText(R.string.lorem_ipsum)
    }

}
