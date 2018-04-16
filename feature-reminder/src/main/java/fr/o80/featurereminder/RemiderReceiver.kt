package fr.o80.featurereminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

/**
 * @author Olivier Perez
 */
class RemiderReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        Timber.d("Reminder triggered")
    }
}