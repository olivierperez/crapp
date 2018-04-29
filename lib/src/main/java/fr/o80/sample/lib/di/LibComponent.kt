package fr.o80.sample.lib.di

import dagger.Component
import fr.o80.crapp.data.ProjectRepository
import fr.o80.crapp.data.TimesheetRepository
import fr.o80.sample.lib.core.LibConfiguration
import fr.o80.sample.lib.core.ui.LibNavigationView
import fr.o80.sample.lib.ui.MainActivity
import javax.inject.Singleton

/**
 * @author Olivier Perez
 */
@Component(modules = [LibModule::class])
@Singleton
interface LibComponent {
    fun inject(activity: MainActivity)

    fun inject(view: LibNavigationView)

    fun libConfiguration(): LibConfiguration

    fun projectRepository(): ProjectRepository

    fun timesheetRepository(): TimesheetRepository
}
