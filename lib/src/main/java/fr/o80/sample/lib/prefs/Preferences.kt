package fr.o80.sample.lib.prefs

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

/**
 * Represents a single [SharedPreferences] file.
 *
 * Usage:
 *
 * `Preferences.init(context)`
 *
 * ```kotlin
 * class UserPreferences : Preferences() {
 *   var emailAccount by stringPref("key", "default value")
 *   var showSystemAppsPreference by booleanPref("key", false)
 * }
 * ```
 *
 * @author Olivier Perez inspired from David Whitman - https://gist.github.com/davidwhitman/b83e1744e8435a2c8cba262c1179f1a8
 */
abstract class Preferences(val context: Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
    }

    private val listeners = mutableListOf<SharedPrefsListener>()

    abstract class PrefDelegate<T>(val prefKey: String) {

        abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
        abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
    }

    interface SharedPrefsListener {
        fun onSharedPrefChanged(property: KProperty<*>)
    }

    fun addListener(sharedPrefsListener: SharedPrefsListener) {
        listeners.add(sharedPrefsListener)
    }

    fun removeListener(sharedPrefsListener: SharedPrefsListener) {
        listeners.remove(sharedPrefsListener)
    }

    fun clearListeners() = listeners.clear()

    fun stringPref(prefKey: String, defaultValue: String) = StringPrefDelegate(prefKey, defaultValue)

    inner class StringPrefDelegate(prefKey: String, private val defaultValue: String) : PrefDelegate<String>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): String = prefs.getString(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            prefs.edit().putString(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    fun intPref(prefKey: String, defaultValue: Int) = IntPrefDelegate(prefKey, defaultValue)

    inner class IntPrefDelegate(prefKey: String, private val defaultValue: Int) : PrefDelegate<Int>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getInt(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            prefs.edit().putInt(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    fun floatPref(prefKey: String, defaultValue: Float) = FloatPrefDelegate(prefKey, defaultValue)

    inner class FloatPrefDelegate(prefKey: String, private val defaultValue: Float) : PrefDelegate<Float>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getFloat(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
            prefs.edit().putFloat(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    fun booleanPref(prefKey: String, defaultValue: Boolean) = BooleanPrefDelegate(prefKey, defaultValue)

    inner class BooleanPrefDelegate(prefKey: String, private val defaultValue: Boolean) : PrefDelegate<Boolean>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getBoolean(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
            prefs.edit().putBoolean(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    fun longPref(prefKey: String, defaultValue: Long) = LongPrefDelegate(prefKey, defaultValue)

    inner class LongPrefDelegate(prefKey: String, private val defaultValue: Long) : PrefDelegate<Long>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getLong(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
            prefs.edit().putLong(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    fun stringSetPref(prefKey: String, defaultValue: Set<String>) = StringSetPrefDelegate(prefKey, defaultValue)

    inner class StringSetPrefDelegate(prefKey: String, private val defaultValue: Set<String>) : PrefDelegate<Set<String>>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> = prefs.getStringSet(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) {
            prefs.edit().putStringSet(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    fun intArrayPref(prefKey: String, defaultValue: IntArray) = IntArrayPrefDelegate(prefKey, defaultValue)

    inner class IntArrayPrefDelegate(prefKey: String, private val defaultValue: IntArray) : PrefDelegate<IntArray>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): IntArray = prefs.getIntArray(prefKey, defaultValue)

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: IntArray) {
            prefs.edit().putIntArray(prefKey, value).apply()
            onPrefChanged(property)
        }
    }

    private fun onPrefChanged(property: KProperty<*>) {
        listeners.forEach { it.onSharedPrefChanged(property) }
    }

    fun SharedPreferences.Editor.putIntArray(key: String, value: IntArray): SharedPreferences.Editor {
        return putString(key, value.joinToString(
                separator = ",",
                transform = { it.toString() }))
    }

    fun SharedPreferences.getIntArray(key: String, defaultValue: IntArray): IntArray {
        with(getString(key, "")) {
            with(if(isNotEmpty()) split(',') else return defaultValue) {
                return IntArray(count(), { this[it].toInt() })
            }
        }
    }
}