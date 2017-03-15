package fr.o80.sample.lib.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Olivier Perez
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureScope {
}
