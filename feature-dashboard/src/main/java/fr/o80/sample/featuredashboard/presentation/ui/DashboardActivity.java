package fr.o80.sample.featuredashboard.presentation.ui;

import android.app.Fragment;

import fr.o80.sample.featuredashboard.dagger.DaggerDashboardComponent;
import fr.o80.sample.featuredashboard.dagger.DashboardComponent;
import fr.o80.sample.featuredashboard.dagger.DashboardModule;
import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.ui.BaseDrawerActivity;

/**
 * @author Olivier Perez
 */
public class DashboardActivity extends BaseDrawerActivity {

    private DashboardComponent component;

    @Override
    protected void initDagger() {
        component = DaggerDashboardComponent.builder()
                .libComponent(((LibApplication) getApplication()).component())
                .build();
    }

    @Override
    protected Fragment getInitFragment() {
        return new DashboardFragment();
    }

    public DashboardComponent component() {
        return component;
    }
}
