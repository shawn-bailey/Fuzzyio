package com.shnoozie.fuzzyio.service

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.session.legacy.PlaybackStateCompat
import mediaSession

class NoiseService: MediaSessionService() {

    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        val player = ExoPlayer.Builder(this).build()
        mediaSession = MediaSession.Builder(this, player).build()


        val stateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY
                        or PlaybackStateCompat.ACTION_PLAY_PAUSE
            )

        mediaSession.setPlaybackState(stateBuilder.build())

        // MySessionCallback() has methods that handle callbacks from a media controller
        mediaSession.setCallback(MySessionCallback())

        // Set the session's token so that client activities can communicate with it.
        setSessionToken(mediaSession.sessionToken)
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

}