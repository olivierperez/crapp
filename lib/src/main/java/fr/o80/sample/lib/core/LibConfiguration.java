package fr.o80.sample.lib.core;

/**
 * @author Olivier Perez
 */
public class LibConfiguration {

    private Feature homeFeature;

    public Feature getHome() {
        return homeFeature;
    }

    public static class Builder {

        private Feature homeFeature;

        public LibConfiguration build() {
            LibConfiguration conf = new LibConfiguration();
            conf.homeFeature = homeFeature;
            return conf;
        }

        public Builder homeFeature(Feature feature) {
            homeFeature = feature;
            return this;
        }
    }
}
