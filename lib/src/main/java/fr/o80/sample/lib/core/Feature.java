package fr.o80.sample.lib.core;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * @author Olivier Perez
 */
public interface Feature {
    void open(Context context);

    @StringRes
    int getTitle();

    @DrawableRes
    int getIcon();
}
