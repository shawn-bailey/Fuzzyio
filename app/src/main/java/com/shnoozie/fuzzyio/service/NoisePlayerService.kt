package com.shnoozie.fuzzyio.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.shnoozie.fuzzyio.R

const val PLAY_PAUSE = "PLAY_PAUSE"

class NoisePlayerService : Service() {

    val binder = Binder()

    var mediaPlayer = MediaPlayer()

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        super.onStartCommand(intent, flags, startId)

        return START_STICKY
    }

    inner class noiseBinder: Binder() {
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private fun sendNotification() {

        val style = androidx.media3. .NotificationCompat.MediaStyle()


        val icon = if (mediaPlayer.isPlaying) {
            androidx.media3.session.R.drawable.media3_icon_pause
        } else {
            androidx.media3.session.R.drawable.media3_icon_play
        }
        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Noise Player")
            .setContentText("Playing noise")
            .setSmallIcon( (R.drawable.ic_launcher_foreground))
            .addAction(icon, "Play", createActionIntent())
            .build()

    }

    fun createActionIntent(): PendingIntent {
        val intent = Intent(this, NoisePlayerService::class.java).apply {
            action = PLAY_PAUSE
        }
        return PendingIntent.getService(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }



}