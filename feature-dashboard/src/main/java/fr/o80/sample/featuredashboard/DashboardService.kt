package fr.o80.sample.featuredashboard

import javax.inject.Inject

import fr.o80.sample.lib.core.LibConfiguration

/**
 * @author Olivier Perez
 */
class DashboardService @Inject constructor(private val configuration: LibConfiguration) {

    val title: String
        get() = "Dashboard title " + configuration
}
