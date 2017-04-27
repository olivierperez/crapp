package fr.o80.sample.lib.core.presenter;

/**
 * @author Olivier Perez
 */
public class Presenter<T extends PresenterView> {

    private T view;

    public void attach(T view) {
        this.view = view;
    }

    public void dettach() {
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
}
