package com.farhad.jollyjournal.com.farhad.jollyjournal.view.article

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ArticleRoute(
    viewModel: ArticleViewModel,
    popBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    ArticleScreen(uiState, popBack)
}