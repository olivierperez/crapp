package fr.o80.sample.timesheet.presentation.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

import fr.o80.sample.lib.core.ui.BaseDrawerActivity;

/**
 * @author Olivier Perez
 */
public class TimesheetActivity extends BaseDrawerActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, TimesheetActivity.class);
    }

    @Override
    protected Fragment getInitFragment() {
        return null;
    }
}
