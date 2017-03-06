package fr.o80.sample.featuredashboard.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fr.o80.sample.featuredashboard.R;

/**
 * @author Olivier Perez
 */
public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        ((TextView)findViewById(R.id.textView)).setText("Dashboard");
    }
}
