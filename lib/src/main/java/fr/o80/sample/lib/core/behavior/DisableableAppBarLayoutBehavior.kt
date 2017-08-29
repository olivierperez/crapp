package fr.o80.sample.lib.core.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * AppBarLayout behavior that can prevent the component to expand.
 * By default, the behavior allow to exppand.

 * @author Olivier Perez
 */
class DisableableAppBarLayoutBehavior : AppBarLayout.Behavior {

    var isEnabled = true

    constructor()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean =
            isEnabled && super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
}
