package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.common.PremiumIndicator

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: NewsItem.Article
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(112.dp)
                ) {
                    AsyncImage(
                        model = article.imageURL,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Box(
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Color(0x88000000))
                            .padding(8.dp)
                    ) {
                        if (article.isPaid) {
                            PremiumIndicator()
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = article.headline,
                        fontSize = 20.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = article.description,
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        article.hashtags.take(3).forEach { hashtag ->
                            Text(
                                text = hashtag,
                                color = Color.Blue,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clickable {
                                        Toast
                                            .makeText(context, hashtag, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}