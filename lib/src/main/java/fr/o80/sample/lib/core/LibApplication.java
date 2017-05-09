package fr.o80.sample.lib.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

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
