//package com.shnoozie.fuzzyio.service
//
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.graphics.Color
//import android.os.Build
//import android.os.IBinder
//import androidx.core.app.NotificationCompat
//import com.shnoozie.fuzzyio.R
//
//class MediaServiceOne : Service() {
//    private val CHANNEL_ID = "MediaServiceChannel"
//    private val NOTIFICATION_ID = 1
//    private var isPlaying = false
//
//    override fun onCreate() {
//        super.onCreate()
//        createNotificationChannel()
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        when (intent?.action) {
//            "ACTION_PLAY" -> {
//                isPlaying = true
//                showNotification()
//            }
//            "ACTION_PAUSE" -> {
//                isPlaying = false
//                showNotification()
//            }
//            "ACTION_STOP" -> {
//                stopForeground(true)
//                stopSelf()
//            }
//        }
//        return START_NOT_STICKY
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val serviceChannel = NotificationChannel(
//                CHANNEL_ID,
//                "Media Service Channel",
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            val manager = getSystemService(NotificationManager::class.java)
//            manager.createNotificationChannel(serviceChannel)
//        }
//    }
//
//    private fun showNotification() {
//        val playPauseAction = NotificationCompat.Action(
//            if (isPlaying) R.drawable.pause_icon else R.drawable.play_icon,
//            if (isPlaying) "Pause" else "Play",
//            PendingIntent.getService(
//                this, 0, Intent(this, MediaServiceOne::class.java).apply {
//                    action = if (isPlaying) "ACTION_PAUSE" else "ACTION_PLAY"
//                }, PendingIntent.FLAG_IMMUTABLE
//            )
//        )
//
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Media Player")
//            .setContentText("Playing music...")
//            .setSmallIcon(R.drawable.play_icon)
//            .setColor(Color.YELLOW)
//            .addAction(playPauseAction)
//            .setStyle(androidx.media.app.NotificationCompat.MediaStyle())
//            .build()
//
//        startForeground(NOTIFICATION_ID, notification)
//    }
//}
