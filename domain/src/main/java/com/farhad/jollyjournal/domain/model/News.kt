package com.farhad.jollyjournal.domain.model

sealed class News {

    data class Article(
        val type: NewsType,
        val imageURL: String,
        val headline: String,
        val description: String,
        val articleURL: String,
        val hashtags: List<String>,
        val isPaid: Boolean
    ) : News()

    data class Video(
        val type: NewsType,
        val imageURL: String,
        val headline: String,
        val videoType: String,
        val duration: String,
        val videoURL: String,
        val isPaid: Boolean
    ) : News()
}

enum class NewsType(val typeName: String) {
    ARTICLE("Article"),
    VIDEO("Video");

    companion object {
        infix fun from(typeName: String): NewsType =
            NewsType.values().firstOrNull { it.typeName == typeName }
                ?: throw TypeCastException("Unknown NewsRoomEntityType: $typeName")
    }
}
