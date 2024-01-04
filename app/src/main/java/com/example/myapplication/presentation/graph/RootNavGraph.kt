package com.example.myapplication.presentation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.MainScreen
import com.example.myapplication.presentation.screens.NewFeedScreen.NewFeedDetailScreen.NewFeedDetailScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.MAIN
    ) {
        composable(route = Graph.MAIN) {
            MainScreen()
        }
        composable(route = Graph.NEW_FEED_DETAIL) { backStackEntry ->
            val newsUrl = backStackEntry.arguments?.getString("newsUrl")
            newsUrl?.let {
                NewFeedDetailScreen(newsUrl)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val DETAILS = "details_graph"
    const val NEW_FEED_DETAIL = "new_feed_detail_graph"

}