package fr.o80.sample.lib.core.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);

        Presenter presenter = presenter();
        if (presenter != null) {
            presenter.attach(this);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        Presenter presenter = presenter();
        if (presenter != null) {
            presenter.dettach();
        }

        super.onDestroyView();
        unbinder.unbind();

        // Hide keyboard
        View view = getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract Presenter presenter();

}
