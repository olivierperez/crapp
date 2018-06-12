package fr.o80.sample.lib.prefs

import android.content.Context
import java.util.Calendar
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
    var reminderDays: IntArray by intArrayPref("reminder:days", intArrayOf(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY))
}