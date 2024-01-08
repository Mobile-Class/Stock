package com.example.myapplication.presentation.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers
import com.example.myapplication.domain.model.userWatchList
import com.example.myapplication.presentation.screens.SearchScreen.CompanyListingsEvent
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
                    item{
                        RowList("Your Watchlist", state.userWatchList)
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

@Composable
fun RowList(title: String, list: List<Any>?) {
    if (list != null && list.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
            if (list != null && list.isNotEmpty()) {
                LazyRow {
                    items(list.take(3)) { item ->
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
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
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
fun WatchlistItem(item: userWatchList)
{
    GainerItemContent(item.ticker, item.price, item.changeAmount, item.changePercentage, item.volume)
}



@Composable
fun GainerItemContent(ticker: String, price: String, changeAmount: String, changePercentage: String, volume: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Ticker: $ticker",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Price: $price",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Change Amount: $changeAmount",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Change Percentage: $changePercentage",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Volume: $volume",
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
