package fr.o80.sample.lib.utils

import java.util.Calendar
import java.util.Date

/**
 * @author Olivier Perez
 */

fun today(): Date =
        Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

fun mkdate(year: Int, month: Int, dayOfMonth: Int): Date =
        Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

fun firstDayOfMonth(date: Date): Date =
        Calendar.getInstance().apply {
            time = date
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

fun lastDayOfMonth(date: Date): Date =
        Calendar.getInstance().apply {
            time = date
            set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.time

operator fun Date.plus(days: Int): Date =
        Calendar.getInstance()
                .apply {
                    this.time = this@plus
                    add(Calendar.DAY_OF_MONTH, days)
                }.time
