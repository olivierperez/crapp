package fr.o80.sample.lib.di

import dagger.Module
import dagger.Provides
import fr.o80.sample.lib.core.LibConfiguration
import javax.inject.Singleton

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
