package fr.o80.sample.lib.prefs

import android.content.Context
import javax.inject.Inject

/**
 * @author Olivier Perez
 */
class User
@Inject constructor(context: Context) : Preferences(context) {
    var email: String by stringPref("summary:email", "")
    var rememberEmail: Boolean by booleanPref("summary:rememberEmail", false)

    var reminderHour: Int by intPref("remidner:hour", 18)
    var reminderMinute: Int by intPref("reminder:minute", 0)
}