package com.example.stock_analyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.stock_analyzer.graph.RootNavigationGraph
import com.example.stock_analyzer.ui.theme.StockAnalyzerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAnalyzerTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}
