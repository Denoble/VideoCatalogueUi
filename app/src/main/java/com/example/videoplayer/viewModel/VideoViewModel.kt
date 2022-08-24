package com.example.videoplayer.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.model.Video
import com.example.videoplayer.model.VideoCategory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class VideoViewModel() : ViewModel() {
    val streamCategoryList: MutableState<List<VideoCategory>> =
        mutableStateOf(ArrayList<VideoCategory>())
    init {
        updateStreamCategoryList()
    }

    private fun updateStreamCategoryList() {
        viewModelScope.launch {
            val steamCategoryListUpdateJob: Deferred<Flow<List<VideoCategory>>> =
                async(Dispatchers.Default) {
                        prepareStreamCategories()
                }
            try {
                val streamCategoryListValue = steamCategoryListUpdateJob.await()
                 streamCategoryListValue.collect{
                     streamCategoryList.value =it
                }
            }catch (e:Exception){
                Log.e(VideoViewModel::class.simpleName+"streamCategoryUpdate", e.stackTraceToString())
            }


        }
    }

    private fun prepareStreamCategories(): Flow<List<VideoCategory>> {
        val tempStreamCategoryList = ArrayList<VideoCategory>()
        val category = "Category"
        return flow {
            for (i in 0 until 10) {
                val tempList = ArrayList<Video>()
                val tempCategory = category + "  " + i
                for (j in 0 until 20) {
                    val tempVideo = Video(Uri.parse("R.drawable.img").toString(), "Stream $j")
                    tempList.add(tempVideo)
                }
                val tempVideoCategory = VideoCategory(tempCategory, tempList)
                tempStreamCategoryList.add(tempVideoCategory)
            }
            emit(tempStreamCategoryList)
        }.flowOn(Dispatchers.Main)
    }
}