package be.helmo.simon.viewmodels

import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class SoundPlayer(private val context: Context) {

    private var player: MediaPlayer? = null

    fun playSound(soundResource: Int) {
        player?.release()
        player = MediaPlayer.create(context, soundResource).apply {
            setOnCompletionListener { release() }
            start()
        }
    }
}