package fr.o80.sample.lib.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Perez
 */
public class LibConfiguration {

    private Feature homeFeature;

    private List<Feature> features;

    public Feature getHome() {
        return homeFeature;
    }

    public List<Feature> features() {
        return features;
    }

    public static class Builder {

        private Feature homeFeature;

        private List<Feature> features = new ArrayList<>();

        public LibConfiguration build() {
            LibConfiguration conf = new LibConfiguration();
            conf.homeFeature = homeFeature;
            conf.features = features;
            return conf;
        }

        public Builder homeFeature(Feature feature) {
            homeFeature = feature;
            features.add(feature);
            return this;
        }

        public Builder addFeature(Feature feature) {
            features.add(feature);
            return this;
        }
    }
}
