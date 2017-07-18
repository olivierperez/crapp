package fr.o80.sample.featuredashboard.dagger

import dagger.Module
import dagger.Provides
import fr.o80.sample.featuredashboard.DashboardService
import fr.o80.sample.lib.core.LibConfiguration
import fr.o80.sample.lib.dagger.FeatureScope

/**
 * @author Olivier Perez
 */
@Module
class DashboardModule {

    @Provides
    @FeatureScope
    internal fun provideDashboardService(configuration: LibConfiguration): DashboardService {
        return DashboardService(configuration)
    }

}
