package com.farhad.jollyjournal.com.farhad.jollyjournal.view.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val article: NewsItem.Article? = savedStateHandle[ARG_ARTICLE]

    init {
        println(article)
    }
}