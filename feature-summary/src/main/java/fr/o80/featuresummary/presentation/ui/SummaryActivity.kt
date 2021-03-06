package fr.o80.featuresummary.presentation.ui

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import fr.o80.featuresummary.R
import fr.o80.featuresummary.dagger.DaggerSummaryComponent
import fr.o80.featuresummary.dagger.SummaryComponent
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.ui.BaseDrawerActivity

/**
 * @author Olivier Perez
 */
class SummaryActivity : BaseDrawerActivity() {

    lateinit var component: SummaryComponent
        private set

    override val layoutId: Int
        get() = R.layout.activity_drawer_w_toolbar

    override val initFragment: Fragment
        get() = SummaryFragment.newInstance()

    override fun initDagger() {
        component = DaggerSummaryComponent.builder()
                .libComponent((application as LibApplication).component)
                .build()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SummaryActivity::class.java)
        }
    }
}