package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    navigateToVideo: () -> Unit,
    navigateToArticle: () -> Unit,
) {
    Column {
        Text(
            modifier = Modifier.clickable {
                navigateToVideo()
            },
            text = "navigate to video"
        )
        Text(
            modifier = Modifier.clickable {
                navigateToArticle()
            },
            text = "navigate to article"
        )
    }
}