package com.example.myapplication.presentation.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.screens.DetailScreen.DetailScreen
import com.example.myapplication.presentation.screens.HomeScreen.HomeScreen
import com.example.myapplication.presentation.screens.NewFeedScreen.NewFeedScreen
import com.example.myapplication.presentation.screens.SearchScreen.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Search.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                HomeScreen()
            }
        }
        composable(route = BottomBarScreen.Search.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                SearchScreen(navController)
            }
        }

        composable(route = BottomBarScreen.NewFeed.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                NewFeedScreen()
            }
        }

        composable(route = Graph.DETAILS) {
            DetailScreen(navController)
        }
    }
}
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "Home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search : BottomBarScreen(
        route = "Search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object NewFeed : BottomBarScreen(
        route = "NewFeed",
        title = "NewFeed",
        icon = Icons.Default.Star
    )
}
