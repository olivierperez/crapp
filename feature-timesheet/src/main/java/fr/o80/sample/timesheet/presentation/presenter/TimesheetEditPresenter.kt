package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.crapp.data.entity.Project
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

    private var editingProject: Long = 0

    fun init(project: Project? = null) {
        if (project == null) {
            editingProject = 0
            view.initFields("", "")
        } else {
            editingProject = project.id
            view.initFields(project.label!!, project.code!!)
        }
    }

    fun onValidateClicked(label: String, code: String) {
        if (editingProject == 0L) {
            addDisposable(projectCrud
                    .create(label, code)
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
        } else {
            addDisposable(projectCrud
                    .update(editingProject, label, code)
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

    fun onDeleteClicked() {
        addDisposable(projectCrud
                .archive(editingProject)
                .subscribeBy(onSuccess = {
                    Timber.d("Project deleted %sd", editingProject)
                    view.finish()
                }, onError = {
                    Timber.e(it, "Failed to delete %d", editingProject)
                    view.showError(R.string.error_failed_to_save_project, it.javaClass.simpleName)
                })
        )
    }

}