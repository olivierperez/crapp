package fr.o80.featureqrscan.presentation.ui;

import android.app.Fragment;

import fr.o80.featureqrscan.DaggerQRScanComponent;
import fr.o80.featureqrscan.QRScanComponent;
import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.ui.BaseDrawerActivity;

/**
 * @author Olivier Perez
 */
public class QRScanActivity extends BaseDrawerActivity {

    private QRScanComponent component;

    @Override
    protected void initDagger() {
        component = DaggerQRScanComponent.builder()
                .libComponent(((LibApplication) getApplication()).component())
                .build();
    }

    @Override
    protected Fragment getInitFragment() {
        return QRScanFragment.newInstance();
    }

    public QRScanComponent component() {
        return component;
    }
}
