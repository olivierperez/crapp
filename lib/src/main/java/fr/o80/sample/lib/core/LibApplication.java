package fr.o80.sample.lib.core;

import android.app.Application;

/**
 * @author Olivier Perez
 */

public abstract class LibApplication extends Application {

    protected abstract LibConfiguration buildLibconfiguration();
}
