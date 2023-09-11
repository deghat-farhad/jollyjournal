package com.farhad.jollyjournal.view.article

import androidx.lifecycle.SavedStateHandle
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItemType
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.article.ARG_ARTICLE
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.article.ArticleViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ArticleViewModelTest {

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun initialStateShouldBeLoadedWithArticle() {
        val dummyArticle = NewsItem.Article(
            type = NewsItemType.ARTICLE,
            imageURL = "https://example.com/article.jpg",
            headline = "Sample Article Headline",
            description = "Sample Article Description",
            articleURL = "https://example.com/article",
            hashtags = listOf("#sample", "#news"),
            isPaid = false
        )

        `when`(savedStateHandle.get<Any>(ARG_ARTICLE)).thenReturn(dummyArticle)

        val viewModel = ArticleViewModel(savedStateHandle)
        val initialState = viewModel.state.value
        assertTrue(initialState is ArticleViewModel.UiState.Loaded)
        val loadedState = initialState as ArticleViewModel.UiState.Loaded
        assertEquals("Sample Article Headline", loadedState.article.headline)
    }

    @Test
    fun initialStateShouldBeErrorWhenArticleIsNull() {
        val viewModel = ArticleViewModel(savedStateHandle)

        val initialState = viewModel.state.value
        assertTrue(initialState is ArticleViewModel.UiState.Error)
    }
}