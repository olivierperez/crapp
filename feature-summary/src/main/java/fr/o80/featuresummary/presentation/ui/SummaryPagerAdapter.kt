package fr.o80.featuresummary.presentation.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import fr.o80.featuresummary.usecase.model.ProjectSummary

/**
 * @author Olivier Perez
 */
class SummaryPagerAdapter(fm: FragmentManager, private val projectIds: List<Long>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment =
            SummaryProjectFragment.newInstance(projectIds[position])

    override fun getCount(): Int =
            projectIds.size

}
