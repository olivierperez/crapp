package fr.o80.sample.timesheet.presentation.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.ui.BaseDrawerActivity;
import fr.o80.sample.timesheet.R;
import fr.o80.sample.timesheet.dagger.DaggerTimesheetComponent;
import fr.o80.sample.timesheet.dagger.TimesheetComponent;

/**
 * @author Olivier Perez
 */
public class TimesheetActivity extends BaseDrawerActivity {

    private TimesheetComponent component;

    public static Intent newIntent(Context context) {
        return new Intent(context, TimesheetActivity.class);
    }

    @Override
    protected void initDagger() {
        component = DaggerTimesheetComponent.builder()
                .libComponent(((LibApplication) getApplication()).component())
                .build();
    }

    @Override
    protected Fragment getInitFragment() {
        return TimesheetEntriesFragment.newInstance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer_simple;
    }

    public TimesheetComponent component() {
        return component;
    }
}
