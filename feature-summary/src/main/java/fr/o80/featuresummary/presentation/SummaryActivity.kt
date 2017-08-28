package fr.o80.featuresummary.presentation

import android.app.Fragment
import android.content.Context
import android.content.Intent
import fr.o80.sample.lib.core.ui.BaseDrawerActivity

/**
 * @author Olivier Perez
 */
class SummaryActivity : BaseDrawerActivity() {
    override val initFragment: Fragment
        get() = TODO("not implemented")

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SummaryActivity::class.java)
        }
    }
}