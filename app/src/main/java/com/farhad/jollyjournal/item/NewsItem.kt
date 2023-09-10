package com.farhad.jollyjournal.com.farhad.jollyjournal.item

sealed class NewsItem {

    data class Article(
        val type: NewsItemType,
        val imageURL: String,
        val headline: String,
        val description: String,
        val articleURL: String,
        val hashtags: List<String>,
        val isPaid: Boolean
    ) : NewsItem()

    data class Video(
        val type: NewsItemType,
        val imageURL: String,
        val headline: String,
        val videoType: String,
        val duration: String,
        val videoURL: String,
        val isPaid: Boolean
    ) : NewsItem()
}

enum class NewsItemType(val typeName: String) {
    ARTICLE("Article"),
    VIDEO("Video");

    companion object {
        infix fun from(typeName: String): NewsItemType =
            NewsItemType.values().firstOrNull { it.typeName == typeName }
                ?: throw TypeCastException("Unknown NewsRoomEntityType: $typeName")
    }
}
