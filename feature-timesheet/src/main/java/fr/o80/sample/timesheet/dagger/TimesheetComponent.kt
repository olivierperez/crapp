package fr.o80.sample.timesheet.dagger

import dagger.Component
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.di.LibComponent
import fr.o80.sample.timesheet.presentation.ui.TimesheetEditFragment
import fr.o80.sample.timesheet.presentation.ui.TimesheetEntriesFragment

/**
 * @author Olivier Perez
 */
@Component(dependencies = arrayOf(LibComponent::class), modules = arrayOf(TimesheetModule::class))
@FeatureScope
interface TimesheetComponent {
    fun inject(fragment: TimesheetEntriesFragment)
    fun inject(fragment: TimesheetEditFragment)
}
