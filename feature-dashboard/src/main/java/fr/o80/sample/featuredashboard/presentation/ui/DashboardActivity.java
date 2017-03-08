package fr.o80.sample.featuredashboard.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.o80.sample.featuredashboard.R;
import fr.o80.sample.featuredashboard.dagger.DaggerDashboardComponent;
import fr.o80.sample.featuredashboard.dagger.DashboardComponent;
import fr.o80.sample.featuredashboard.dagger.DashboardModule;
import fr.o80.sample.lib.core.LibApplication;

/**
 * @author Olivier Perez
 */
public class DashboardActivity extends AppCompatActivity {

    private DashboardComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        component = DaggerDashboardComponent.builder()
                .libComponent(((LibApplication) getApplication()).component())
                .dashboardModule(new DashboardModule())
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new DashboardFragment())
                .commit();
    }

    public DashboardComponent component() {
        return component;
    }
}
