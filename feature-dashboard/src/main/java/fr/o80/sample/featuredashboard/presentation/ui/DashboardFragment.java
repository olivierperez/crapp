package fr.o80.sample.featuredashboard.presentation.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import fr.o80.sample.featuredashboard.DashboardService;
import fr.o80.sample.featuredashboard.R;
import fr.o80.sample.lib.core.presenter.Presenter;
import fr.o80.sample.lib.core.ui.BaseFragment;

/**
 * @author Olivier Perez
 */
public class DashboardFragment extends BaseFragment {

    @Inject
    protected DashboardService dashboardService;

    @Override
    public void onResume() {
        super.onResume();
        ((DashboardActivity)getActivity()).component().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.textView)).setText(R.string.lorem_ipsum);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected Presenter presenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
