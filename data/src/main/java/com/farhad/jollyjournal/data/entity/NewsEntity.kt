package com.farhad.jollyjournal.data.entity

import com.farhad.jollyjournal.data.utils.NewsDeserializer
import com.farhad.jollyjournal.data.utils.NewsEntityTypeSerializer
import kotlinx.serialization.Serializable

@Serializable(with = NewsDeserializer::class)
sealed class NewsEntity{
    @Serializable
    data class Article(
        val type: NewsEntityType,
        val imageURL: String,
        val headline: String,
        val description: String,
        val articleURL: String,
        val hashtags: List<String>,
        val isPaid: Boolean,
    ) : NewsEntity()

    @Serializable
    data class Video(
        val type: NewsEntityType,
        val imageURL: String,
        val headline: String,
        val videoType: String,
        val duration: String,
        val videoURL: String,
        val isPaid: Boolean,
    ) : NewsEntity()
}

@Serializable(with = NewsEntityTypeSerializer::class)
enum class NewsEntityType(val typeName: String) {
    ARTICLE("Article"),
    VIDEO("Video");

    companion object {
        infix fun from(typeName: String): NewsEntityType =
            NewsEntityType.values().firstOrNull { it.typeName == typeName }
                ?: throw TypeCastException("Unknown NewsRoomEntityType: $typeName")
    }
}

