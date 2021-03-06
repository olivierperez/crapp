package fr.o80.featuresummary.dagger

import dagger.Component
import fr.o80.featuresummary.presentation.ui.SummaryFragment
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.di.LibComponent

/**
 * @author Olivier Perez
 */
@Component(dependencies = [LibComponent::class], modules = [])
@FeatureScope
interface SummaryComponent {
    fun inject(fragment: SummaryFragment)
}