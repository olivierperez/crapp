package fr.o80.sample.lib.core.ui

import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import fr.o80.sample.lib.R
import fr.o80.sample.lib.core.Feature
import fr.o80.sample.lib.core.LibConfiguration
import kotlinx.android.synthetic.main.activity_drawer_w_appbar.*
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
abstract class BaseDrawerActivity : AppCompatActivity(), LibNavigationView.Listener {

    lateinit private var drawerToggle: ActionBarDrawerToggle

    @Inject
    lateinit var libConfiguration: LibConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        setSupportActionBar(toolbar)

        supportFragmentManager.findFragmentById(R.id.main_container).let {
            initFragment.let {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, initFragment)
                        .commit()
            }
        }

        drawerToggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                R.string.drawer_open,
                R.string.drawer_close

        )

        // Set the drawer toggle as the DrawerListener
        drawer_layout.addDrawerListener(drawerToggle)

        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        initDagger()

    }

    @get:LayoutRes
    protected open val layoutId: Int = R.layout.activity_drawer_w_appbar

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState()
    }

    override fun onResume() {
        super.onResume()

        navigationView.setListener(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item)
    }

    override fun onFeatureClicked(feature: Feature) {
        drawer_layout.closeDrawers()
        when {
            feature.notYetOpened(this) -> {
                feature.open(this)
            }
            else -> {

            }
        }
    }

    protected abstract val initFragment: Fragment?

    protected open fun initDagger() {

    }
}
