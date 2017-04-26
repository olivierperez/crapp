package fr.o80.sample.lib.core.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * AppBarLayout behavior that can prevent the component to expand.
 * By default, the behavior allow to exppand.
 *
 * @author Olivier Perez
 */
public class DisableableAppBarLayoutBehavior extends AppBarLayout.Behavior {

    private boolean enabled = true;

    public DisableableAppBarLayoutBehavior() {
    }

    public DisableableAppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return enabled && super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
    }

    public boolean isEnabled() {
        return enabled;
    }
}
