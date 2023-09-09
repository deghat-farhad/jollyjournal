package com.farhad.jollyjournal.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: NewsRoomEntityType,
    val imageURL: String,
    val headline: String,
    val isPaid: Boolean,
    val description: String?, // Nullable for Video
    val articleURL: String?, // Nullable for Video
    val hashtags: List<String>?, // Nullable for Video
    val videoType: String?, // Nullable for Article
    val duration: String?, // Nullable for Article
    val videoURL: String? // Nullable for Article
)

enum class NewsRoomEntityType(val typeName: String) {
    ARTICLE("Article"),
    VIDEO("Video");

    companion object {
        infix fun from(typeName: String): NewsRoomEntityType =
            NewsRoomEntityType.values().firstOrNull { it.typeName == typeName }
                ?: throw TypeCastException("Unknown NewsRoomEntityType: $typeName")
    }
}