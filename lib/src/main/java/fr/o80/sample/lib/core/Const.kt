package fr.o80.sample.lib.core

import java.util.Calendar

/**
 * @author Olivier Perez
 */
object Const {
    const val KEY_ENTRIES = "KEY_ENTRIES"
    const val KEY_TOTALHOURS = "KEY_TOTALHOURS"
    const val KEY_DATE = "KEY_DATE"
    const val HOURS_PER_DAY = 8

    const val REQUEST_CODE_REMINDER_ALARM = 1

    val NOTIFIABLE_DAYS: IntArray = intArrayOf(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY)
}