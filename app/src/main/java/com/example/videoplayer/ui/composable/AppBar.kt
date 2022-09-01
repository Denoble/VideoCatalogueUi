package com.example.videoplayer.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.videoplayer.R

@Composable
fun CustomAppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            Icon(
                icon,
                contentDescription =title,
                modifier = Modifier.clickable(onClick = { iconClickAction.invoke() })
            )
        }
    )
}