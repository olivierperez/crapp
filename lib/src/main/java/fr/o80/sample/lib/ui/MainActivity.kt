package fr.o80.sample.lib.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fr.o80.sample.lib.core.LibApplication
import fr.o80.sample.lib.core.LibConfiguration
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var libConfiguration: LibConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as LibApplication).component.inject(this)

        with(libConfiguration) {
            home.open(this@MainActivity)
        }

        finish()
    }
}
