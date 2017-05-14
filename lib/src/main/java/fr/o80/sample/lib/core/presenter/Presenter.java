package fr.o80.sample.lib.core.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Olivier Perez
 */
public class Presenter<T extends PresenterView> {

    private T view;

    private CompositeDisposable disposables = new CompositeDisposable();

    public void attach(T view) {
        this.view = view;
    }

    public void dettach() {
        disposables.dispose();
        view = null;
    }

    public T getView() {
        if (view == null) {
            throw new RuntimeException("Trying to access view after it has been dettached.");
        }
        return view;
    }

    public T getViewUnsafe() {
        return view;
    }

    protected void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
