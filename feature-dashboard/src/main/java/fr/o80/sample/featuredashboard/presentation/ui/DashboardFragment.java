package fr.o80.sample.featuredashboard.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import fr.o80.sample.featuredashboard.DashboardService;
import fr.o80.sample.featuredashboard.R;
import fr.o80.sample.featuredashboard.R2;
import fr.o80.sample.lib.core.ui.BaseFragment;

/**
 * @author Olivier Perez
 */
public class DashboardFragment extends BaseFragment {

    @Inject
    protected DashboardService dashboardService;

    @BindView(R2.id.textView)
    protected TextView textView;

    @Override
    public void onResume() {
        super.onResume();
        ((DashboardActivity)getActivity()).component().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView.setText(R.string.lorem_ipsum);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }
}
