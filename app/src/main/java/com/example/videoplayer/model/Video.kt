package com.example.videoplayer.model

import android.net.Uri

data class Video(val uri: String, val title:String)

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Details : Routes("details")
}
data class VideoCategory(val category:String,val list:List<Video>)