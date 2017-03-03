package fr.o80.sample.sampleapplication;

import fr.o80.sample.featuredashboard.feature.Dashboard;
import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.LibConfiguration;

/**
 * @author Olivier Perez
 */
public class AppApplication extends LibApplication {

    @Override
    protected LibConfiguration buildLibconfiguration() {
        return new LibConfiguration.Builder()
                .homeFeature(new Dashboard())
                .build();
    }

}
