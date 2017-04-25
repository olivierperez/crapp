package fr.o80.sample.timesheet;

import android.content.Context;
import android.content.Intent;

import fr.o80.sample.lib.core.Feature;
import fr.o80.sample.timesheet.presentation.ui.TimesheetActivity;

/**
 * @author Olivier Perez
 */
public class Timesheet implements Feature {
    @Override
    public void open(Context context) {
        Intent intent = TimesheetActivity.newIntent(context);
        context.startActivity(intent);
    }

    @Override
    public int getTitle() {
        return R.string.timesheet_title;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_timesheet;
    }
}
