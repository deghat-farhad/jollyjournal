package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home

import androidx.compose.runtime.Composable
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItemType

val dummyArticle = NewsItem.Article(
    type = NewsItemType.ARTICLE,
    imageURL = "https://example.com/image.jpg",
    headline = "Lorem Ipsum",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    articleURL = "https://example.com/article",
    hashtags = listOf("#Lorem", "#Ipsum", "#News"),
    isPaid = false
)
val dummyVideo = NewsItem.Video(
    type = NewsItemType.VIDEO,
    imageURL = "https://example.com/video-thumbnail.jpg",
    headline = "Video Title",
    videoType = "mp4",
    duration = "10:00",
    videoURL = "https://example.com/video.mp4",
    isPaid = false
)

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    navigateToVideo: (video: NewsItem.Video) -> Unit,
    navigateToArticle: (article: NewsItem.Article) -> Unit,
) {
    HomeScreen(
        navigateToVideo = {
            navigateToVideo(dummyVideo)
        },
        navigateToArticle = {
            navigateToArticle(dummyArticle)
        }
    )
}