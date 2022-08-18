package com.example.videoplayer

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.RawResourceDataSource

class CustomPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_player)

        val playerControlView = findViewById<PlayerView>(R.id.custom_player_holder)

        val uri = RawResourceDataSource.buildRawResourceUri(R.raw.test_video)

        val exoPlayer = ExoPlayer.Builder(this)
            .setSeekForwardIncrementMs(10000)
            .setSeekBackIncrementMs(10000)
            .build().apply {
                setMediaItem(MediaItem.fromUri(uri))
                prepare()
                playWhenReady = true
            }

        playerControlView.visibility = View.VISIBLE
        playerControlView.player = exoPlayer
    }
}