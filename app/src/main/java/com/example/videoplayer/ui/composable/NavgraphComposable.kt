package com.example.videoplayer.ui.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.videoplayer.model.Routes

@Composable
fun AppNavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route){

    }
}