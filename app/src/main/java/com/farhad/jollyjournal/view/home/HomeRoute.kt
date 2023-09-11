package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.ui.HomeScreen

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    navigateToVideo: (video: NewsItem.Video) -> Unit,
    navigateToArticle: (article: NewsItem.Article) -> Unit,
) {
    val uiState by viewModel.state.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.retry()
    }
    HomeScreen(
        uiState = uiState,
        retry = viewModel::retry,
        navigateToVideo = { video ->
            navigateToVideo(video)
        },
        navigateToArticle = { article ->
            navigateToArticle(article)
        }
    )
}