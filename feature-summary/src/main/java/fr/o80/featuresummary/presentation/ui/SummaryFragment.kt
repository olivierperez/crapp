package fr.o80.featuresummary.presentation.ui

import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import fr.o80.featuresummary.R
import fr.o80.featuresummary.presentation.presenter.LoadedSummaryUiModel
import fr.o80.featuresummary.presentation.presenter.SummaryPresenter
import fr.o80.featuresummary.presentation.presenter.SummaryView
import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.core.presenter.PresenterView
import fr.o80.sample.lib.core.ui.BaseFragment
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.fragment_summary.*
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class SummaryFragment : BaseFragment(), SummaryView {

    @Inject
    lateinit var presenter: SummaryPresenter

    private var showSendOption = false

    private val adapter = SummaryProjectAdapter()

    override val layoutId: Int
        get() = R.layout.fragment_summary

    override fun presenter(): Presenter<PresenterView> = presenter

    override fun inject() {
        (activity as SummaryActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = SlideInLeftAnimator()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        presenter.init()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.summary_menu, menu)
        menu.findItem(R.id.menu_send).isVisible = showSendOption
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_send -> {
                presenter.onSendClicked()
                true
            }
            else -> false
        }
    }

    override fun showEmailPopup() {
        AlertDialog.Builder(context!!)
                .setTitle(R.string.summary_email)
                .setView(R.layout.popup_email)
                .setPositiveButton("Ok") { dialog, _ ->
                    (dialog as AlertDialog).let {
                        val email = dialog.findViewById<EditText>(R.id.email)?.text?.toString().orEmpty()
                        val rememberEmail = dialog.findViewById<CheckBox>(R.id.rememberEmail)?.isChecked ?: false
                        presenter.onEmailChoosen(email, rememberEmail)
                    }
                }
                .show()
    }

    override fun send(email: String, title: String, body: String) {
        val intent = ShareCompat.IntentBuilder.from(activity)
                .setType("message/rfc822")
                .setSubject(title)
                .setHtmlText(body)
                .addEmailTo(email)
                .intent
        startActivity(intent)
    }

    override fun showSendOption() {
        showSendOption = true
        activity?.invalidateOptionsMenu()
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
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