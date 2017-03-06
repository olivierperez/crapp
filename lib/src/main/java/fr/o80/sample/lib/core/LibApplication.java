package fr.o80.sample.lib.core;

import android.app.Application;

import fr.o80.sample.lib.di.DaggerLibComponent;
import fr.o80.sample.lib.di.LibComponent;
import fr.o80.sample.lib.di.LibModule;

/**
 * @author Olivier Perez
 */

public abstract class LibApplication extends Application {

    protected abstract LibConfiguration buildLibConfiguration();

    private LibComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerLibComponent.builder()
                .libModule(new LibModule(buildLibConfiguration()))
                .build();
    }

    public LibComponent component() {
        return component;
    }
}
