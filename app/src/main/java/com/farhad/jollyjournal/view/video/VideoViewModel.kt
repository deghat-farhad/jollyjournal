package com.farhad.jollyjournal.com.farhad.jollyjournal.view.video

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val video: NewsItem.Video? = savedStateHandle[ARG_VIDEO]

    init {
        println(video)
    }
}