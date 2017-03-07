package fr.o80.sample.featuredashboard;

import javax.inject.Inject;

import fr.o80.sample.lib.core.LibConfiguration;

/**
 * @author Olivier Perez
 */
public class DashboardService {

    private final LibConfiguration configuration;

    @Inject
    public DashboardService(LibConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getTitle() {
        return "Dashboard title " + configuration;
    }
}
