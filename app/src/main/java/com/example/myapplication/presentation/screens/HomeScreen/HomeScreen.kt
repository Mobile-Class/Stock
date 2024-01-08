package com.example.myapplication.presentation.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.state.value

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isRefreshing
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        // SwipeRefresh
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(HomeScreenEvent.Refresh)
            }
        ) {

            if(state.topGainers == null || state.topLosers == null || state.mostActivelyTraded == null) {
                Text(
                    text = "Loading",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
            else {
                // LazyColumn
                LazyColumn {
                    item {
                        RowList("Top Gainers", state.topGainers)
                    }
                    item {
                        RowList("Top Losers", state.topLosers)
                    }
                    item {
                        RowList("Most Actively Traded", state.mostActivelyTraded)
                    }
                }
            }
        }

        // Loading or Error Handling
        when {
            state.isLoading -> {
                // Display loading indicator
                CircularProgressIndicator()
            }
            state.error != null -> {
                // Handle error
                Text(text = state.error!!, color = MaterialTheme.colors.error)
            }
        }

    }
}

// ...

@Composable
fun RowList(title: String, list: List<Any>?) {
    var selectedItemCount by remember { mutableStateOf(5) }
    var expanded by remember { mutableStateOf(false) }

    if (list != null && list.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    color = MaterialTheme.colors.onBackground
                )

                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)

                ) {
                    Box(
                        modifier = Modifier
                            .clickable { expanded = true }
                            .padding(8.dp)
                    ) {
                        Text(text = "$selectedItemCount items ▼")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .wrapContentSize()  // Adjust width and height based on content
                            .background(MaterialTheme.colors.background)
                    ) {
                        // Add dropdown items (e.g., 5, 10, 15)
                        listOf(5, 10, 15).forEach { itemCount ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedItemCount = itemCount
                                    expanded = false
                                    // You may want to notify the ViewModel or update the list here
                                }
                            ) {
                                Text(text = "$itemCount items")
                            }
                        }
                    }
                }
            }

            if (list.isNotEmpty()) {
                LazyRow(
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(list.take(selectedItemCount)) { item ->
                        when (item) {
                            is topGainers -> GainerItem(item)
                            is topLosers -> LosersItem(item)
                            is mostActivelyTraded -> ActivelyTradedItem(item)
                        }
                    }
                }
            } else {
                // Display a message when the list is empty
                Text(
                    text = "List is empty",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

@Composable
fun GainerItem(item: topGainers) {
    GainerItemContent(item.ticker, item.price, item.changeAmount, item.changePercentage, item.volume)
}

@Composable
fun LosersItem(item: topLosers) {
    GainerItemContent(item.ticker, item.price, item.changeAmount, item.changePercentage, item.volume)
}

@Composable
fun ActivelyTradedItem(item: mostActivelyTraded) {
    GainerItemContent(item.ticker, item.price, item.changeAmount, item.changePercentage, item.volume)
}

@Composable
fun ActivelyTradedItemContent(ticker: String, price: String, changeAmount: String, changePercentage: String, volume: String) {
        Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFF222222)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = " $ticker ", // Add spaces around ticker for visual separation
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prc: $price",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (changeAmount.startsWith('-')) "▼ $changeAmount" else "▲ $changeAmount",
                style = MaterialTheme.typography.body1,
                color = if (changeAmount.startsWith('-')) Color(0xFFFF0000) else Color(0xFF00FF00)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$changePercentage",
                style = MaterialTheme.typography.body1,
                color = if (changeAmount.startsWith('-')) Color(0xFFFF0000) else Color(0xFF00FF00)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Vol: ${abbreviateVolume(volume)}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}


@Composable
fun GainerItemContent(ticker: String, price: String, changeAmount: String, changePercentage: String, volume: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFFFFFFF)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = " $ticker ", // Add spaces around ticker for visual separation
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
//                modifier = Modifier
//                    .background(MaterialTheme.colors.primary)
//                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Prc: $price",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (changeAmount.startsWith('-')) "▼ $changeAmount" else "▲ $changeAmount",
                style = MaterialTheme.typography.body1,
                color = if (changeAmount.startsWith('-')) Color(0xFFFF0000) else Color(0xFF00FF00)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$changePercentage",
                style = MaterialTheme.typography.body1,
                color = if (changeAmount.startsWith('-')) Color(0xFFFF0000) else Color(0xFF00FF00)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Vol: ${abbreviateVolume(volume)}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

private fun abbreviateVolume(volume: String): String {
    val volumeValue = volume.toDouble()
    return when {
        volumeValue >= 1e6 -> "${String.format("%.2f", volumeValue / 1e6)}M"
        volumeValue >= 1e3 -> "${String.format("%.2f", volumeValue / 1e3)}K"
        else -> volume
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
