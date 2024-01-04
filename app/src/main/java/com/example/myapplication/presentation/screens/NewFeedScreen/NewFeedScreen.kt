    package com.example.myapplication.presentation.screens.NewFeedScreen

    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.IntrinsicSize
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.paddingFromBaseline
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material.Button
    import androidx.compose.material.Card
    import androidx.compose.material.CircularProgressIndicator
    import androidx.compose.material.Icon
    import androidx.compose.material.MaterialTheme
    import androidx.compose.material.OutlinedTextField
    import androidx.compose.material.Text
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Search
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.navigation.NavHostController
    import com.example.myapplication.domain.model.News
    import com.example.myapplication.presentation.graph.Graph
    import com.example.myapplication.presentation.screens.HomeScreen.HomeScreenEvent
    import com.google.accompanist.swiperefresh.SwipeRefresh
    import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

    @Composable
    fun NewFeedScreen() {
        val viewModel = hiltViewModel<NewFeedViewModel>()
        val state = viewModel.state.value
        val swipeRefreshState = rememberSwipeRefreshState(
            isRefreshing = state.isRefreshing
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {

                // SwipeRefresh
                ButtonRow(
                    buttons = listOf(
                        "Technology",
                        "Life Sciences",
                        "Finance",
                        "Manufacturing",
                        "Economy"
                    ),
                    selectedCategory = state.selectedCategory,
                    onButtonClick = { category ->
                        // Update the selected category and trigger a reload of news
                        viewModel.onEvent(NewFeedEvent.OnCategorySelected(category))
                    }
                )

                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        viewModel.onEvent(NewFeedEvent.Refresh)
                    }
                ) {
    //                Text(
    //                    text = "News",
    //                    style = MaterialTheme.typography.h5,
    //                    fontWeight = FontWeight.Bold,
    //                    color = MaterialTheme.colors.primary,
    //                    modifier = Modifier
    //                        .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
    //                )

    //            OutlinedTextField(
    //                value = state.searchQuery,
    //                onValueChange = { newQuery ->
    //                    viewModel.onEvent(NewFeedEvent.OnSearchQueryChange(newQuery))
    //                },
    //                label = { Text("Search for a menu item") },
    ////                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
    //                modifier = Modifier
    //                    .fillMaxWidth()
    //                    .padding(vertical = 16.dp)
    //            )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.primary)
                    ) {
                        items(state.news ?: emptyList()) { news ->
                            NewsItem(news, onNewsClick = {
                                // Trigger the new event to navigate to the detail screen with the URL
                                viewModel.onEvent(NewFeedEvent.NavigateToDetail(it))

                            })
                        }
                    }
                }
            }
            CircularIndeterminateProgressBar(isDisplayed = state.isLoading)
        }
    }


    @Composable
    fun ButtonRow(
        buttons: List<String>,
        selectedCategory: String,
        onButtonClick: (String) -> Unit
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(buttons) { button ->
                val backgroundColor = if (button == selectedCategory) {
                    MaterialTheme.colors.secondary
                    // Set the selected button color
                } else {

                    MaterialTheme.colors.primary
                    // Set the unselected button color
                }

                Button(
                    onClick = { onButtonClick(button) },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .height(IntrinsicSize.Max)
                        .background(backgroundColor)
                ) {
                    Text(text = button)
                }
            }
        }
    }

    @Composable
    fun NewsItem(news: News, onNewsClick: (String) -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = 8.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onNewsClick(news.url ?: "") // Truyền URL khi tin tức được click
                    }
            ) {
                Text(
                    text = "Title: ${news.title ?: "N/A"}",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Summary: ${news.summary ?: "N/A"}")
            }
        }
    }


    @Composable
    fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
        if (isDisplayed) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary,
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewNewFeedScreen() {
        NewFeedScreen()
    }