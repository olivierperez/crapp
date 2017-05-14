package fr.o80.sample.lib.core.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.o80.sample.lib.core.presenter.Presenter;
import fr.o80.sample.lib.core.presenter.PresenterView;

/**
 * Base Fragment for every
 *
 * @author Olivier Perez
 */
public abstract class BaseFragment extends Fragment implements PresenterView {

    private Unbinder unbinder;
    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = presenter();
        if (presenter != null) {
            presenter.attach(this);
        }
    }

    @Override
    public void onPause() {
        if (presenter != null) {
            presenter.dettach();
        }
        super.onPause();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract Presenter presenter();

}
