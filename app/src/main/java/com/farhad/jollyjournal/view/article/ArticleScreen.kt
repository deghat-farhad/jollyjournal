package com.farhad.jollyjournal.com.farhad.jollyjournal.view.article

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
fun ArticleScreen(uiState: ArticleViewModel.UiState, popBack: () -> Unit) = when (uiState) {
    ArticleViewModel.UiState.Error -> {
        ErrorScreen(retry = popBack)
    }

    is ArticleViewModel.UiState.Loaded -> {
        val article = uiState.article
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Display the article image
            AsyncImage(
                model = article.imageURL,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the headline
            Text(
                text = article.headline,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display the description
            Text(
                text = article.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display hashtags
            if (article.hashtags.isNotEmpty()) {
                Text(
                    text = "Hashtags: ${article.hashtags.joinToString(", ")}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display the article URL
            Text(
                text = "Article URL: ${article.articleURL}",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}