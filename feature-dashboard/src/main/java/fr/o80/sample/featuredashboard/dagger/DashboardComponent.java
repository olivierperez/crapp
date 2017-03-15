package fr.o80.sample.featuredashboard.dagger;

import dagger.Component;
import fr.o80.sample.featuredashboard.presentation.ui.DashboardFragment;
import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.lib.di.LibComponent;

/**
 * @author Olivier Perez
 */
@Component(dependencies = LibComponent.class, modules = DashboardModule.class)
@FeatureScope
public interface DashboardComponent {
    void inject(DashboardFragment dashboardFragment);
}
