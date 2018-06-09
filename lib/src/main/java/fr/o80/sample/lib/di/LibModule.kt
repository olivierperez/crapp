package fr.o80.sample.lib.di

import android.content.Context
import dagger.Module
import dagger.Provides
import fr.o80.sample.lib.core.LibConfiguration
import javax.inject.Singleton

/**
 * @author Olivier Perez
 */
@Module
class LibModule(private val configuration: LibConfiguration, private val context: Context) {

    @Provides
    @Singleton
    fun provideLibConfiguration(): LibConfiguration {
        return configuration
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

}
