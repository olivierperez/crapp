package fr.o80.sample.featuredashboard.presentation.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import fr.o80.sample.featuredashboard.DashboardService;
import fr.o80.sample.featuredashboard.R;

/**
 * @author Olivier Perez
 */
public class DashboardFragment extends Fragment {

    @Inject
    protected DashboardService dashboardService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((DashboardActivity)getActivity()).component().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.textView)).setText(R.string.lorem_ipsum);
        Toast.makeText(getContext(), dashboardService.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
