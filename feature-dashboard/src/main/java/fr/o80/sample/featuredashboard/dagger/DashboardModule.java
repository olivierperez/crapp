package fr.o80.sample.featuredashboard.dagger;

import dagger.Module;
import dagger.Provides;
import fr.o80.sample.featuredashboard.DashboardService;
import fr.o80.sample.lib.core.LibConfiguration;

/**
 * @author Olivier Perez
 */
@Module
public class DashboardModule {

    @Provides
    @FeatureScope
    DashboardService provideDashboardService(LibConfiguration configuration) {
        return new DashboardService(configuration);
    }

}
