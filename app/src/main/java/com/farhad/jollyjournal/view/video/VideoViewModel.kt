package com.farhad.jollyjournal.com.farhad.jollyjournal.view.video

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val video: NewsItem.Video? = savedStateHandle[ARG_VIDEO]

    private val mutableState: MutableStateFlow<UiState> = MutableStateFlow(
        video?.let {
            UiState.Loaded(it)
        } ?: UiState.Error
    )
    val state: StateFlow<UiState> = mutableState

    sealed class UiState {
        object Error : UiState()
        data class Loaded(
            val video: NewsItem.Video
        ) : UiState()
    }
}