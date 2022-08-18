package com.example.videoplayer.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.videoplayer.R


@Composable
fun CustomImage(
    url: String,
    description:String,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder //Used while loading
                (LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true) //Crossfade animation between images
                    placeholder(R.drawable.loading_animation) //Used while loading
                    fallback(R.drawable.ic_baseline_broken_image_24) //Used if data is null
                    error(R.drawable.ic_baseline_broken_image_24) //Used when loading returns with error
                }).build()
        )


    Image(
        modifier = modifier,
        //Use painter in Image composable
        painter = painter,
        contentScale = contentScale,
        contentDescription = description
    )
}