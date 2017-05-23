package fr.o80.sample.lib.core

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

/**
 * @author Olivier Perez
 */
interface Feature {
    fun open(context: Context)

    @get:StringRes
    val title: Int

    @get:DrawableRes
    val icon: Int
}
