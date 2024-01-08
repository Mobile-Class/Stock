    package com.example.myapplication.presentation.screens.NewFeedScreen

    import android.util.Log
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import java.text.SimpleDateFormat
    import java.util.Date
    import java.util.Locale
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material.Button
    import androidx.compose.material.Card
    import androidx.compose.material.CircularProgressIndicator
    import androidx.compose.material.MaterialTheme
    import androidx.compose.material.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.rememberUpdatedState
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.hilt.navigation.compose.hiltViewModel
    import coil.compose.rememberImagePainter
    import com.example.myapplication.domain.model.News
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
//                            .background(MaterialTheme.colors.primary)
                    ) {
                        items(state.news ?: emptyList()) { news ->
                            NewsItem(news,
                                onNewsClick = {
//                                viewModel.onEvent(NewFeedEvent.NavigateToDetail(it))
                            }
                            )
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
        val selectedCategoryState = rememberUpdatedState(selectedCategory)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(buttons) { button ->
                val isButtonSelected = button == selectedCategoryState.value

                Column(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { onButtonClick(button) }
                        .clip(MaterialTheme.shapes.small)
                ) {
                    Box(
                        modifier = Modifier
                            .background(if (isButtonSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary)
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small)
                    ) {
                        Text(
                            text = button,
                            color = MaterialTheme.colors.background,
                            fontWeight = if (isButtonSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
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
                        onNewsClick(news.url ?: "")
                    }
            ) {
                // Banner Image
                news.banner_image?.let { imageUrl ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(MaterialTheme.shapes.medium)
                    ) {
                        Image(
                            painter = rememberImagePainter(imageUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                ) {
                    Text(
                        text = "Title: ${news.title ?: "N/A"}",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small)
                    )
                }



                Spacer(modifier = Modifier.height(8.dp))

                // Summary
                Text(text = "Summary: ${news.summary ?: "N/A"}")

                Spacer(modifier = Modifier.height(8.dp))

                // Source

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.End
                ){
                    Text(
                        text = "${news.source ?: "N/A"}",

                        modifier = Modifier
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small))
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Time Published:

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
//                        text = "${news.time_published}",
                        text = formatNewsTime(news.time_published), //

                        modifier = Modifier
                            .padding(8.dp)
                            .clip(MaterialTheme.shapes.small)
                    )
                }
            }
        }
    }
    fun formatNewsTime(time: String?): String {
        return try {
            val originalFormat = SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.getDefault())
            val parsedDate = originalFormat.parse(time ?: "") ?: Date()

            val newFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            val formattedDate = newFormat.format(parsedDate)

            Log.d("DateFormat", "Original: $time, Formatted: $formattedDate")

            formattedDate
        } catch (e: Exception) {
            Log.e("DateFormat", "Error formatting date: $e")
            "N/A"
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