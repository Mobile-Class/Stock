package com.example.stock_analyzer.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stock_analyzer.BottomBarScreen
import com.example.stock_analyzer.screens.ScreenContent
@Composable
fun HomeNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                ScreenContent(
                    name = BottomBarScreen.Home.route,
                    onClick = {
                        navController.navigate(Graph.DETAILS)
                    }
                )
            }
        }
        composable(route = BottomBarScreen.Search.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                ScreenContent(
                    name = BottomBarScreen.Search.route,
                    onClick = { }
                )
            }
        }
        composable(route = BottomBarScreen.Watchlist.route) {
            Box(modifier = Modifier.padding(paddingValues)) {
                ScreenContent(
                    name = BottomBarScreen.Watchlist.route,
                    onClick = { }
                )
            }
        }
    }
}

