package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.mapper.NewsItemMapper
import com.farhad.jollyjournal.domain.usecase.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews,
    private val newsItemMapper: NewsItemMapper
) : ViewModel() {
    private val mutableState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> = mutableState

    fun retry() {
        viewModelScope.launch {
            mutableState.value = UiState.Loading
            try {
                getNews()
                    .onSuccess { newsList ->
                        mutableState.value = UiState.Loaded(newsItemMapper.toPresentation(newsList))
                    }
                    .onFailure {
                        mutableState.value = UiState.Error
                    }
            } catch (e: Exception) {
                mutableState.value = UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object Error : UiState()
        data class Loaded(
            val news: List<NewsItem>
        ) : UiState()
    }
}