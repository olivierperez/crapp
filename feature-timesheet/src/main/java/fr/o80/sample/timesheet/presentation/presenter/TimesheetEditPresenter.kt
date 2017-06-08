package fr.o80.sample.timesheet.presentation.presenter

import fr.o80.sample.lib.core.presenter.Presenter
import fr.o80.sample.lib.dagger.FeatureScope
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
@FeatureScope
class TimesheetEditPresenter @Inject
constructor() :  Presenter<TimesheetEditView>() {
    fun onButtonClicked(name: String, label: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}