package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.graph.Graph
import com.example.myapplication.presentation.screens.HomeScreen
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
                SearchScreen()
            }
        }
        composable(route = BottomBarScreen.Watchlist.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                HomeScreen()
            }
        }
    }
}