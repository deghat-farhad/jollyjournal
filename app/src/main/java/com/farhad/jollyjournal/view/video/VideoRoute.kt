package com.farhad.jollyjournal.com.farhad.jollyjournal.view.video

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun VideoRoute(
    viewModel: VideoViewModel,
    popBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    VideoScreen(uiState, popBack)
}