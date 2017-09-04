package fr.o80.featuresummary.presentation.ui

import android.app.Fragment
import android.content.Context
import android.content.Intent
import fr.o80.featuresummary.dagger.DaggerSummaryComponent
import fr.o80.featuresummary.dagger.SummaryComponent
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.ui.BaseDrawerActivity

/**
 * @author Olivier Perez
 */
class SummaryActivity : BaseDrawerActivity() {

    private lateinit var component: SummaryComponent

    override val initFragment: Fragment
        get() = SummaryFragment.newInstance()

    override fun initDagger() {
        component = DaggerSummaryComponent.builder()
                .libComponent((application as LibApplication).component())
                .build()
    }

    fun component(): SummaryComponent = component

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SummaryActivity::class.java)
        }
    }
}