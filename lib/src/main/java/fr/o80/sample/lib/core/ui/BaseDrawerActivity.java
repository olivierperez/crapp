package fr.o80.sample.lib.core.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.o80.sample.lib.core.Feature;
import sample.o80.fr.lib.R;
import sample.o80.fr.lib.R2;

/**
 * @author Olivier Perez
 */
public abstract class BaseDrawerActivity extends AppCompatActivity implements LibNavigationView.Listener {

    @BindView(R2.id.navigation_view)
    protected LibNavigationView libNavigationView;

    @BindView(R2.id.drawer_layout)
    protected DrawerLayout drawer;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.main_container) == null) {
            Fragment initFragment = getInitFragment();

            if (initFragment != null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, initFragment)
                        .commit();
            }
        }

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawer,
                R.string.drawer_open,
                R.string.drawer_close

        );

        // Set the drawer toggle as the DrawerListener
        drawer.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }


    @Override
    protected void onResume() {
        super.onResume();

        initDagger();

        libNavigationView.setListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFeatureClicked(Feature feature) {
        drawer.closeDrawers();
        feature.open(this);
    }

    protected abstract Fragment getInitFragment();

    protected void initDagger() {

    }
}
