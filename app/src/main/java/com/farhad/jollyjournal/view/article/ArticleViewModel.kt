package com.farhad.jollyjournal.com.farhad.jollyjournal.view.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val article: NewsItem.Article? = savedStateHandle[ARG_ARTICLE]

    private val mutableState: MutableStateFlow<UiState> = MutableStateFlow(
        article?.let {
            UiState.Loaded(it)
        } ?: UiState.Error
    )
    val state: StateFlow<UiState> = mutableState

    sealed class UiState {
        object Error : UiState()
        data class Loaded(
            val article: NewsItem.Article
        ) : UiState()
    }
}