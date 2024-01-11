package com.example.myapplication.presentation.screens.NewFeedScreen

import com.example.myapplication.domain.model.News


sealed class NewFeedEvent {
    object Refresh: NewFeedEvent()
    data class OnCategorySelected(val category: String) : NewFeedEvent()
}