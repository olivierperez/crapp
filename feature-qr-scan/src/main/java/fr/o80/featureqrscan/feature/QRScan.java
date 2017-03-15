package fr.o80.featureqrscan.feature;

import android.content.Context;

import fr.o80.featureqrscan.R;
import fr.o80.sample.lib.core.Feature;

/**
 * @author Olivier Perez
 */
public class QRScan implements Feature {

    @Override
    public void open(Context context) {

    }

    @Override
    public int getTitle() {
        return R.string.qrscan_title;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_qrscan_menu;
    }

}
