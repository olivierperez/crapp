package fr.o80.featureqrscan.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.o80.featureqrscan.DaggerQRScanComponent;
import fr.o80.featureqrscan.QRScanComponent;
import fr.o80.featureqrscan.R;
import fr.o80.sample.lib.core.LibApplication;

/**
 * @author Olivier Perez
 */
public class QRScanActivity extends AppCompatActivity {

    private QRScanComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        component = DaggerQRScanComponent.builder()
                .libComponent(((LibApplication) getApplication()).component())
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new DashboardFragment())
                .commit();*/
    }

    public QRScanComponent component() {
        return component;
    }
}
