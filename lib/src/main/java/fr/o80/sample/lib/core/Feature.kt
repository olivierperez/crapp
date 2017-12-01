package fr.o80.sample.lib.core

import android.app.Activity
import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

/**
 * @author Olivier Perez
 */
interface Feature {

    @get:StringRes
    val title: Int

    @get:DrawableRes
    val icon: Int

    fun open(context: Context)

    fun notYetOpened(activity: Activity): Boolean
}
