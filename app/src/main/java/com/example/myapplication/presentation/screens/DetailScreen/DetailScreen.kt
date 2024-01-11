package com.example.myapplication.presentation.screens.DetailScreen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.presentation.screens.DetailScreen.EvaluationScreen.EvaluationScreen
import com.example.myapplication.presentation.screens.DetailScreen.ProfileScreen.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(navController: NavController, symbol: String, viewModel: CompanyInfoViewModel = hiltViewModel()) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Profile", "Evaluation")

    Column {
        TopAppBar(
            title = { Text("Detail Information for $symbol") }, // Display ticker in the title
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = index == tabIndex,
                    onClick = { tabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        when (tabIndex) {
            0 -> ProfileScreen(symbol, viewModel) // Pass ticker to ProfileScreen
            1 -> EvaluationScreen(symbol, viewModel) // Pass ticker to EvaluationScreen
        }
    }
}
