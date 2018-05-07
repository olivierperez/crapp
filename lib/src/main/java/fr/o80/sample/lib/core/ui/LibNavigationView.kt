package fr.o80.sample.lib.core.ui

import android.content.Context
import android.support.design.widget.NavigationView
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem

import java.lang.ref.WeakReference

import javax.inject.Inject

import fr.o80.sample.lib.core.Feature
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.LibConfiguration

/**
 * @author Olivier Perez
 */
class LibNavigationView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : NavigationView(context, attrs, defStyleAttr) {

    @Inject
    lateinit var configuration: LibConfiguration

    private var listener: WeakReference<Listener>? = null

    init {
        (context.applicationContext as LibApplication).component.inject(this)

        addFeature(0, configuration.home)
        configuration.features
                .withIndex()
                .forEach { (id, feature) ->
                    addFeature(id + 1, feature)
                }
    }

    private fun addFeature(id: Int, feature: Feature) {
        menu
                .add(Menu.NONE, id, Menu.NONE, feature.title)
                .setIcon(feature.icon)
                .setEnabled(true)
                .setOnMenuItemClickListener {
                    val l = listener?.get()
                    if (l != null) {
                        l.onFeatureClicked(feature)
                        true
                    } else
                        false
                }
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
    }

    fun setListener(listener: Listener) {
        this.listener = WeakReference(listener)
    }

    interface Listener {
        fun onFeatureClicked(feature: Feature)
    }

}
