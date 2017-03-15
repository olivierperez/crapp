package fr.o80.featureqrscan.presentation.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author Olivier Perez
 */
public class QRScanFragment extends Fragment {

    public static QRScanFragment newInstance() {
        Bundle args = new Bundle();

        QRScanFragment fragment = new QRScanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //IntentIntegrator.forFragment(this).initiateScan();
    }

}
