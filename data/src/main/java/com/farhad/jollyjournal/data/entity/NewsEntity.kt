package com.farhad.jollyjournal.data.entity

import com.farhad.jollyjournal.data.utils.NewsDeserializer
import kotlinx.serialization.Serializable

@Serializable(with = NewsDeserializer::class)
sealed class NewsEntity{
    @Serializable
    data class Article(
        val type: String,
        val imageURL: String,
        val headline: String,
        val description: String,
        val articleURL: String,
        val hashtags: List<String>,
        val isPaid: Boolean
    ) : NewsEntity()

    @Serializable
    data class Video(
        val type: String,
        val imageURL: String,
        val headline: String,
        val videoType: String,
        val duration: String,
        val videoURL: String,
        val isPaid: Boolean
    ) : NewsEntity()
}

