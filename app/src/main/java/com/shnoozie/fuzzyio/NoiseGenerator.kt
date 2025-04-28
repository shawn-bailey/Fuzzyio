package com.shnoozie.fuzzyio

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack

class NoiseGenerator {

    fun playWhiteNoise(durationMs: Int) {
        val sampleRate = 44100
        val numSamples = durationMs * sampleRate / 1000
        val noise = ByteArray(2 * numSamples) // 16-bit PCM = 2 bytes/sample
        val random = java.util.Random()

        for (i in 0 until numSamples) {
            val sample = (random.nextInt(Short.MAX_VALUE * 2) - Short.MAX_VALUE).toShort()
            noise[2 * i] = (sample.toInt() and 0x00ff).toByte()
            noise[2 * i + 1] = ((sample.toInt() and 0xff00).ushr(8)).toByte()
        }

        val audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            noise.size,
            AudioTrack.MODE_STATIC
        )

        audioTrack.write(noise, 0, noise.size)
        audioTrack.play()
    }
}