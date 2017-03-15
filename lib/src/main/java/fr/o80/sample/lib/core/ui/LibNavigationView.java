package fr.o80.sample.lib.core.ui;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import fr.o80.sample.lib.core.Feature;
import fr.o80.sample.lib.core.LibApplication;
import fr.o80.sample.lib.core.LibConfiguration;

/**
 * @author Olivier Perez
 */
public class LibNavigationView extends NavigationView {

    @Inject
    protected LibConfiguration configuration;

    private WeakReference<Listener> listener;

    public LibNavigationView(Context context) {
        super(context);
        init();
    }

    public LibNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LibNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ((LibApplication) getContext().getApplicationContext()).component().inject(this);
        int id = 0;
        for (final Feature feature : configuration.features()) {
            getMenu().add(Menu.NONE, id++, Menu.NONE, feature.getTitle())
                    .setIcon(feature.getIcon())
                    .setEnabled(true)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (listener != null) {
                                Listener l = listener.get();
                                if (l != null) {
                                    l.onFeatureClicked(feature);
                                    return true;
                                }
                            }
                            return false;
                        }
                    })
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    public void setListener(Listener listener) {
        this.listener = new WeakReference<>(listener);
    }

    public interface Listener {
        void onFeatureClicked(Feature feature);
    }

}
