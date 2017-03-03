package fr.o80.sample.lib.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.o80.sample.lib.core.LibConfiguration;

/**
 * @author Olivier Perez
 */
@Module
public class LibModule {

    private final LibConfiguration configuration;

    public LibModule(LibConfiguration configuration) {
        this.configuration = configuration;
    }

    @Provides
    @Singleton
    public LibConfiguration provideLibConfiguration() {
        return configuration;
    }
}
