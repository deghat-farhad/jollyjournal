package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.common.ErrorScreen
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.common.LoadingScreen
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.HomeViewModel

@Composable
fun HomeScreen(
    uiState: HomeViewModel.UiState,
    retry: () -> Unit,
    navigateToVideo: (NewsItem.Video) -> Unit,
    navigateToArticle: (NewsItem.Article) -> Unit,
) = when (uiState) {
    HomeViewModel.UiState.Loading -> {
        LoadingScreen()
    }

    HomeViewModel.UiState.Error -> {
        ErrorScreen(retry = retry)
    }

    is HomeViewModel.UiState.Loaded -> {
        val newsList = uiState.news
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(newsList) { newsItem ->
                when (newsItem) {
                    is NewsItem.Article -> {
                        ArticleItem(
                            modifier = Modifier.clickable {
                                navigateToArticle(newsItem)
                            },
                            article = newsItem
                        )
                    }

                    is NewsItem.Video -> {
                        VideoItem(
                            modifier = Modifier.clickable {
                                navigateToVideo(newsItem)
                            },
                            video = newsItem
                        )
                    }
                }
            }
        }
    }
}