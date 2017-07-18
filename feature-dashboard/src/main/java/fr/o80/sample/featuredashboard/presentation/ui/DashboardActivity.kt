package fr.o80.sample.featuredashboard.presentation.ui

import android.app.Fragment

import fr.o80.sample.featuredashboard.dagger.DaggerDashboardComponent
import fr.o80.sample.featuredashboard.dagger.DashboardComponent
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.ui.BaseDrawerActivity

/**
 * @author Olivier Perez
 */
class DashboardActivity : BaseDrawerActivity() {

    lateinit private var component: DashboardComponent

    override fun initDagger() {
        component = DaggerDashboardComponent.builder()
                .libComponent((application as LibApplication).component())
                .build()
    }

    override val initFragment: Fragment
        get() = DashboardFragment()

    fun component(): DashboardComponent {
        return component
    }
}
