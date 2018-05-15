package fr.o80.featurereminder.dagger

import dagger.Component
import fr.o80.featurereminder.RemiderReceiver
import fr.o80.featurereminder.usecase.TotalPerDay
import fr.o80.sample.lib.dagger.FeatureScope
import fr.o80.sample.lib.di.LibComponent

@Component(dependencies = [LibComponent::class], modules = [ReminderModule::class])
@FeatureScope
interface ReminderComponent {
    fun inject(receiver: RemiderReceiver)
}