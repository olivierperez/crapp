package fr.o80.sample.lib.di;

import javax.inject.Singleton;

import dagger.Component;
import fr.o80.sample.lib.ui.MainActivity;

/**
 * @author Olivier Perez
 */
@Component(modules = LibModule.class)
@Singleton
public interface LibComponent {
    void inject(MainActivity activity);
}
