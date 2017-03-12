package fr.o80.sample.lib.core.ui;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.MenuItem;

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
        for(Feature feature : configuration.features()) {
            getMenu().add(feature.getTitle())
                    .setIcon(feature.getIcon())
                    .setEnabled(true)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

}
