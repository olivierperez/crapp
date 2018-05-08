package fr.o80.featurereminder

import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat

@DslMarker
annotation class NotificationDsl

fun notification(context: Context, notificationManager: NotificationManager, block: NotificationBuilder.() -> Unit) {
    NotificationBuilder(context, notificationManager).run {
        block()
        show()
    }
}

@NotificationDsl
class NotificationBuilder(private val context: Context, private val notificationManager: NotificationManager) {
    private var channelBuilder: ChannelBuilder? = null
    var id: Int? = null
    var smallIcon: Int? = null
    var title: String? = null
    var text: String? = null
    var autoCancel: Boolean? = null
    var priority: Int? = null
    var intent: PendingIntent? = null

    fun show() {
        val valId = id ?: throw IllegalStateException("Id of notification must be defined")

        channelBuilder?.createChannel(notificationManager)

        val builder = NotificationCompat.Builder(context, RemiderReceiver.CHANNEL_ID).also { builder ->
            builder.setContentTitle(title)
            builder.setContentText(text)
            smallIcon?.let { builder.setSmallIcon(it) }
            autoCancel?.let { builder.setAutoCancel(it) }
            priority?.let { builder.priority = it }
            intent?.let { builder.setContentIntent(it) }
        }

        notificationManager.notify(valId, builder.build())
    }

    fun channel(id: String, block: ChannelBuilder.() -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelBuilder = ChannelBuilder(id).apply {
                block()
            }
        }
    }

    fun intent(context: Context, javaClass: Class<out Activity>, requestCode: Int = 0, flags: Int = 0) {
        intent = PendingIntent.getActivity(context, requestCode, Intent(context, javaClass), flags)
    }

}

@NotificationDsl
class ChannelBuilder(private val id: String) {

    var name: String? = null
    var description: String? = null
    var lockscreenVisibility: Int? = null
    var importance: Int? = null
    var lights: Boolean? = null
    var vibration: Boolean? = null
    var bypassDnd: Boolean? = null

    fun createChannel(notificationManager: NotificationManager) {
        val valImportance = importance ?: throw IllegalStateException("Importance of channel must be defined")
        val valName = name ?: throw IllegalStateException("Name of channel must be defined")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(id, valName, valImportance).also { builder ->
                builder.description = description
                builder.lockscreenVisibility = lockscreenVisibility ?: Notification.VISIBILITY_PRIVATE
                builder.enableLights(false)
                builder.enableVibration(false)
                builder.setBypassDnd(false)
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }
}
