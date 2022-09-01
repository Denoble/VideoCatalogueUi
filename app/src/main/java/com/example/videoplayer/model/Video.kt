package com.example.videoplayer.model

import android.net.Uri

data class Video(val uri: String, val title:String)

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object BasicVideoView : Routes("basicVideoView")
    object CustomizedVideoView : Routes("customizedVideoView")
    object StreamingListView: Routes("StreamingListView")
    object StreamDetailsView : Routes("streamDetailsView")
}
data class VideoCategory(val category:String,val list:List<Video>)