package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.timesheet.data.entity.Project
import fr.o80.sample.timesheet.usecase.ProjectCrud
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetEditPresenter @Inject
constructor() : Presenter<TimesheetEditView>() {

    @Inject
    lateinit var projectCrud: ProjectCrud

    fun onButtonClicked(code: String, label: String) {
        projectCrud.create(Project(code = code, label = label))
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { _ ->
                    view.finish()
                }
    }
}