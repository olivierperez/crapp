package fr.o80.sample.timesheet.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import fr.o80.sample.lib.core.ui.BaseFragment;
import fr.o80.sample.timesheet.R;

/**
 * @author Olivier Perez
 */
public class TimesheetEntriesFragment extends BaseFragment {

    public static TimesheetEntriesFragment newInstance() {
        return new TimesheetEntriesFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_timesheet_entries;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        activity.setSupportActionBar(toolbar);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }
}
