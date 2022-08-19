package com.example.videoplayer.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.videoplayer.R
import com.example.videoplayer.model.Video

@Composable
fun ThumbNail(video: Video, name: String, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .padding(bottom = 48.dp)
            .clickable(onClick = { clickAction.invoke() }),
        elevation = 8.dp,
        shape = Shapes.myshape.medium,
        border = BorderStroke(3.dp, bluegrey),
        backgroundColor = bluegrey
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp)
        ) {
            val (thumbnail, title) = createRefs()
            CustomImage(url = video.uri.toString(),
                description = stringResource(id = R.string.thumbnail_image),
                modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(thumbnail) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    width = Dimension.wrapContent
                    height = Dimension.fillToConstraints
                })
            CustomizedText(text = video.title,
                modifier = Modifier.constrainAs(title) {
                top.linkTo(thumbnail.bottom, margin = 8.dp)
                start.linkTo(thumbnail.start)
                end.linkTo(thumbnail.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            })
        }

    }

}


val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
)
val Shapes.myshape: Shapes
    @Composable
    get() = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = CutCornerShape(topEnd = 16.dp),
        large = RoundedCornerShape(0.dp)
    )
val bluegrey = Color(0xfffafafa)
val Color.lightGrey: Color
    @Composable
    get() = bluegrey