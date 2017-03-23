package fr.o80.featureqrscan.presentation.ui;

import fr.o80.featureqrscan.R;
import fr.o80.sample.lib.core.ui.BaseFragment;

/**
 * @author Olivier Perez
 */
public class QRScanHistory extends BaseFragment {

    public static QRScanHistory newInstance() {
        return new QRScanHistory();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qrscan_history;
    }
    
}
