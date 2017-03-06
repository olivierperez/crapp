package fr.o80.sample.lib.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import fr.o80.sample.lib.core.Feature;
import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.LibConfiguration;

/**
 * @author Olivier Perez
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    protected LibConfiguration libConfiguration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((LibApplication)getApplication()).component().inject(this);

        Feature home = libConfiguration.getHome();
        home.open(this);

        finish();
    }
}
