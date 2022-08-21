package com.example.videoplayer.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.videoplayer.R
import com.example.videoplayer.model.VideoCategory

@Composable
fun HomeScreen(
    list: List<VideoCategory>,
    scaffoldState: ScaffoldState,
    navHostController: NavHostController?
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            CustomAppBar(
                title = stringResource(id = R.string.home_screen_title),
                icon = Icons.Default.Home, {})
        },
        content = {
            it.calculateBottomPadding()
            VideThumbNaillist(categoryList = list, navHostController = navHostController)
        },
        bottomBar = { BottomNavigationBar() })
}

@Composable
fun CustomAppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            Icon(
                icon,
                contentDescription = stringResource(id = R.string.top_app_bar_home_icon_descriptions),
                modifier = Modifier.clickable(onClick = { iconClickAction.invoke() })
            )
        }
    )
}

@Composable
fun BottomNavigationBar(selectedIndex: MutableState<Int> = remember { mutableStateOf(0) }) {
    BottomNavigation(elevation = 10.dp) {
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home, "")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite, "")
        },
            label = { Text(text = "Favorite") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person, "")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }
}

@Composable
fun VideThumbNaillist(
    categoryList: List<VideoCategory>,
    navHostController: NavHostController?
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (lazyColum) = createRefs()
        LazyColumn(
            modifier = Modifier
                .constrainAs(lazyColum) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(Color.White)

        ) {
            items(categoryList) { videoCagetoryItem ->
                ThumbNailList(
                    category = videoCagetoryItem.category,
                    thumbNailImages = videoCagetoryItem.list,
                    navHostController = navHostController
                )
            }

        }
    }

}