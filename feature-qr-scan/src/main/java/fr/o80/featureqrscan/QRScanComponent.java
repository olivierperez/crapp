package fr.o80.featureqrscan;

import dagger.Component;
import fr.o80.sample.lib.dagger.FeatureScope;
import fr.o80.sample.lib.di.LibComponent;

/**
 * @author Olivier Perez
 */
@Component(dependencies = LibComponent.class)
@FeatureScope
public interface QRScanComponent {
}
