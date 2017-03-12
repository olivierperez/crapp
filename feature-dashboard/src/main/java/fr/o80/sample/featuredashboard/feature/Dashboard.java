package fr.o80.sample.featuredashboard.feature;

import android.content.Context;
import android.content.Intent;

import fr.o80.sample.featuredashboard.R;
import fr.o80.sample.featuredashboard.presentation.ui.DashboardActivity;
import fr.o80.sample.lib.core.Feature;

/**
 * @author Olivier Perez
 */
public class Dashboard implements Feature {

    @Override
    public void open(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getTitle() {
        return R.string.feature_title;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_dashboard_menu;
    }

}
