package fr.o80.sample.lib.core.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import sample.o80.fr.lib.R;

/**
 * @author Olivier Perez
 */
public abstract class BaseDrawerActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        initDagger();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.main_container) == null) {
            Fragment initFragment = getInitFragment();

            if (initFragment !=null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, initFragment)
                        .commit();
            }
        }
    }

    protected abstract Fragment getInitFragment();

    protected void initDagger() {

    }
}
