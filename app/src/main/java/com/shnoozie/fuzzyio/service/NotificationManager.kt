package com.shnoozie.fuzzyio.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

private const val NOTIFICATION_CHANNEL_ID = "noise_channel"

class NotificationManager(val context: Context) {

    fun createNotificationChannel() {
        val notificationManager: NotificationManager? =
            context.getSystemService<NotificationManager?>(NotificationManager::class.java)
        if (notificationManager == null) {
            return
        }

        // Incoming call channel
        if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Media Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(null, null)
            channel.enableVibration(false)
            channel.enableLights(false)
            channel.setShowBadge(false)
            notificationManager.createNotificationChannel(channel)
        }
    }
}