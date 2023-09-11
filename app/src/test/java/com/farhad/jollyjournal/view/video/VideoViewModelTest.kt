package com.farhad.jollyjournal.view.video

import androidx.lifecycle.SavedStateHandle
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItemType
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.video.ARG_VIDEO
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.video.VideoViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class VideoViewModelTest {

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }
    @Test
    fun initialStateShouldBeLoadedWithArticle() {
        val dummyVideo = NewsItem.Video(
            type = NewsItemType.VIDEO,
            imageURL = "https://example.com/video.jpg",
            headline = "Sample Video Headline",
            videoType = "Sample Video Type",
            duration = "00:05:00",
            videoURL = "https://example.com/video",
            isPaid = true
        )

        `when`(savedStateHandle.get<Any>(ARG_VIDEO)).thenReturn(dummyVideo)

        val viewModel = VideoViewModel(savedStateHandle)

        val initialState = viewModel.state.value
        assertTrue(initialState is VideoViewModel.UiState.Loaded)
        val loadedState = initialState as VideoViewModel.UiState.Loaded
        assertEquals("Sample Video Headline", loadedState.video.headline)
    }

    @Test
    fun initialStateShouldBeErrorWhenArticleIsNull() {
        val viewModel = VideoViewModel(savedStateHandle)

        val initialState = viewModel.state.value
        assertTrue(initialState is VideoViewModel.UiState.Error)
    }
}