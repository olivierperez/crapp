package fr.o80.sample.lib.utils

import java.util.Calendar
import java.util.Date

/**
 * @author Olivier Perez
 */
object CalendarUtils {
    fun firstDayOfMonth(date: Date): Date =
            Calendar.getInstance().apply {
                time = date
                set(Calendar.DAY_OF_MONTH, 1)
            }.time

    fun lastDayOfMonth(date: Date): Date =
            Calendar.getInstance().apply {
                time = date
                set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            }.time
}