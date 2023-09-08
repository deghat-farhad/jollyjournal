package com.farhad.jollyjournal.data.utils

import com.farhad.jollyjournal.data.entity.NewsEntity
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object NewsDeserializer : JsonContentPolymorphicSerializer<NewsEntity>(NewsEntity::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<NewsEntity> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "Article" -> NewsEntity.Article.serializer()
            "Video" -> NewsEntity.Video.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}