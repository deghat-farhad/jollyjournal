package com.farhad.jollyjournal.com.farhad.jollyjournal.view.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.common.ErrorScreen

@Composable
fun VideoScreen(uiState: VideoViewModel.UiState, popBack: () -> Unit) = when (uiState) {
    VideoViewModel.UiState.Error -> {
        ErrorScreen(retry = popBack)
    }

    is VideoViewModel.UiState.Loaded -> {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val video = uiState.video
            AsyncImage(
                model = video.imageURL, // Replace with your image resource or URL
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the video headline
            Text(
                text = video.headline,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display video details
            Text(
                text = "Video Type: ${video.videoType}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Duration: ${video.duration}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the video URL
            Text(
                text = "Video URL: ${video.videoURL}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}