package com.example.videoplayer.ui.composable

import android.widget.Toast
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.videoplayer.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun LoadingAnimations() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        strokeWidth = 3.dp, color = MaterialTheme.colors.primary
    )
}
@Composable
fun CustomizedText(text: String, modifier: Modifier, textAlign: TextAlign = TextAlign.Start) {
    Text(
        text = text, modifier = modifier, style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold, fontSize = 13.sp,
        ), textAlign = textAlign
    )
}
@Composable
fun CustomImage(
    url: String,
    description:String,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder //Used while loading
                (context).data(data = url)
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