package fr.o80.sample.timesheet.util

import java.util.Calendar
import java.util.Date

/**
 * @author Olivier Perez
 */

operator fun Date.plus(days: Int): Date =
        Calendar.getInstance()
                .apply {
                    this.time = this@plus
                    add(Calendar.DAY_OF_MONTH, days)
                }.time