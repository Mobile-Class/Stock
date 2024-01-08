package com.example.myapplication.presentation.screens.NewFeedScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.data.repository.NewsRepository
import com.example.myapplication.domain.model.News
import com.example.myapplication.presentation.graph.Graph
import kotlinx.coroutines.launch

class NewFeedViewModel() : ViewModel() {
    private lateinit var navController: NavHostController

    private val alphaVantageAPIForJSON = StockApi.createURLForJSON()
    private val stockNewsRepository = NewsRepository(alphaVantageAPIForJSON)
    private val _state = mutableStateOf(NewFeedState())
    val state: State<NewFeedState> get() = _state
//    private var searchJob: Job? = null

    init {
        getNewsList("General") // Set a default category
    }

    fun onEvent(event: NewFeedEvent) {
        when (event) {
            is NewFeedEvent.Refresh -> {
                getNewsList("General")
            }
            is NewFeedEvent.OnCategorySelected -> {
                _state.value = _state.value.copy(selectedCategory = event.category)
                getNewsList(event.category)
            }
            is NewFeedEvent.NavigateToDetail -> {
                println("event url ${event.url}")
                navController.navigate("${Graph.NEW_FEED_DETAIL}?newsUrl=${event.url}")
            }
        }
    }


    private fun getNewsList(category: String) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val filteredNews = when (category) {
                    "General" -> stockNewsRepository.getGeneralNews()
                    else -> stockNewsRepository.getNewsWithCategory(category)
                }
                getGeneralNewsHandleSuccess(filteredNews)
            } catch (e: Exception) {
                handleError(e.message ?: "Unknown error")
            } finally {
                handleLoading(false)
            }
        }
    }

    private fun getGeneralNewsHandleSuccess(data: List<News>?) {
        _state.value = _state.value.copy(
            news = data,
            isLoading = false,
            error = null,
        )
    }

    private fun handleError(errorMessage: String) {
        _state.value = _state.value.copy(
            isLoading = false,
            error = errorMessage
        )
    }

    private fun handleLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }
}