package fr.o80.featuresummary.presentation.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import fr.o80.featuresummary.R
import fr.o80.featuresummary.presentation.presenter.LoadedSummaryUiModel
import fr.o80.featuresummary.presentation.presenter.SummaryPresenter
import fr.o80.featuresummary.presentation.presenter.SummaryUiModel
import fr.o80.featuresummary.presentation.presenter.SummaryView
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.lib.core.ui.BaseFragment
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.fragment_summary.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class SummaryFragment : BaseFragment(), SummaryView {

    @Inject
    lateinit var presenter: SummaryPresenter

    private val adapter = SummaryProjectAdapter()

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
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

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = SlideInLeftAnimator()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        presenter.init()
    }

    override fun update(uiModel: LoadedSummaryUiModel) {
        adapter.update(uiModel.summary)
    }

    override fun showError(stringRes: Int) {
        Toast.makeText(context, stringRes, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = SummaryFragment()
    }
}