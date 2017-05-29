package fr.o80.sample.lib.di

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import fr.o80.sample.lib.core.LibConfiguration

/**
 * @author Olivier Perez
 */
@Module
class LibModule(private val configuration: LibConfiguration) {

    @Provides
    @Singleton
    fun provideLibConfiguration(): LibConfiguration {
        return configuration
    }
}
