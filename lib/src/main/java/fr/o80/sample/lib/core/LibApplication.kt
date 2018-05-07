package fr.o80.sample.lib.core

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager

import fr.o80.sample.lib.di.DaggerLibComponent
import fr.o80.sample.lib.di.LibComponent
import fr.o80.sample.lib.di.LibModule

/**
 * @author Olivier Perez
 */

abstract class LibApplication : Application() {

    protected abstract fun buildLibConfiguration(): LibConfiguration

    lateinit var component: LibComponent
        private set

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerLibComponent.builder()
                .libModule(LibModule(buildLibConfiguration()))
                .build()
        FlowManager.init(FlowConfig.Builder(this)
                .openDatabasesOnInit(true)
                .build())
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }

}
