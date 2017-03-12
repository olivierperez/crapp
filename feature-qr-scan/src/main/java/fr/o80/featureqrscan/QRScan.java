package fr.o80.featureqrscan;

import android.content.Context;

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
        return R.string.feature_title;
    }

    @Override
    public int getIcon() {
        return 0;
    }

}
