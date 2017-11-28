package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.R
import fr.o80.sample.timesheet.usecase.ProjectCrud
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetEditPresenter @Inject
constructor() : Presenter<TimesheetEditView>() {

    @Inject
    lateinit var projectCrud: ProjectCrud

    fun onButtonClicked(label: String, code: String) {
        addDisposable(projectCrud.create(label, code)
                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribeBy(
                                      onSuccess = {
                                          Timber.d("Project saved %s [%s]", label, code)
                                          view.finish()
                                      },
                                      onError = {
                                          Timber.e(it, "Failed to save %s [%s]", label, code)
                                          view.showError(R.string.error_failed_to_save_project, it.javaClass.simpleName)
                                      })
                     )
    }
}