package fr.o80.sample.featuredashboard.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import fr.o80.sample.featuredashboard.DashboardService;
import fr.o80.sample.featuredashboard.R;
import fr.o80.sample.featuredashboard.dagger.DaggerDashboardComponent;
import fr.o80.sample.featuredashboard.dagger.DashboardComponent;
import fr.o80.sample.featuredashboard.dagger.DashboardModule;
import fr.o80.sample.lib.core.LibApplication;

/**
 * @author Olivier Perez
 */
public class DashboardActivity extends AppCompatActivity {

    @Inject
    protected DashboardService dashboardService;

    private DashboardComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        component = DaggerDashboardComponent.builder()
                .libComponent(((LibApplication) getApplication()).component())
                .dashboardModule(new DashboardModule())
                .build();
        component.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((TextView)findViewById(R.id.textView)).setText(dashboardService.getTitle());
    }
}
