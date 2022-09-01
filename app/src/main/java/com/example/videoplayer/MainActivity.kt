package com.example.videoplayer

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.videoplayer.ui.composable.HomeScreen
import com.example.videoplayer.ui.theme.VideoPlayerTheme
import com.example.videoplayer.viewModel.VideoViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.RawResourceDataSource

class MainActivity : ComponentActivity() {

    private lateinit var mediaSession: MediaSessionCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoPlayerTheme {
                val navController = rememberNavController()
                val viewModel: VideoViewModel by viewModels()
                val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainPage(navController = navController)
                    }
                    composable("BasicVideoView") {
                        Surface(color = MaterialTheme.colors.background) {
                            BasicVideoView()
                        }
                    }
                    composable("CustomizedVideoView") {
                        Surface(color = MaterialTheme.colors.background) {
                            CustomizedVideoView()
                        }
                    }
                    composable("StreamingListView") {
                        HomeScreen(
                            list = viewModel.streamCategoryList.value,
                            scaffoldState = scaffoldState,
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomizedVideoView() {
    val context = LocalContext.current

    val uri = RawResourceDataSource.buildRawResourceUri(R.raw.test_video)

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekForwardIncrementMs(10000)
            .setSeekBackIncrementMs(10000)
            .build().apply {
                setMediaItem(MediaItem.fromUri(uri))
                prepare()
                playWhenReady = true
            }
    }

    AndroidView(factory = {
        PlayerView(it).apply {
            player = exoPlayer
            setShowNextButton(false)
            setShowPreviousButton(false)
        }
    })
}

@Composable
fun BasicVideoView() {
    val context = LocalContext.current

    val uri = RawResourceDataSource.buildRawResourceUri(R.raw.test_video)

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
        }
    }

    AndroidView(factory = {
        StyledPlayerView(it).apply {
            player = exoPlayer
        }
    })
}

@Composable
fun MainPage(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        CustomButton(text = "BasicVideoView") {
            navController.navigate("BasicVideoView")
        }

        CustomButton(text = "CustomizedVideoView") {
            navController.navigate("CustomizedVideoView")
        }
        CustomButton(text = "StreamingListView") {
            navController.navigate("StreamingListView")
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan)
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VideoPlayerTheme {
        Greeting("Android")
    }
}