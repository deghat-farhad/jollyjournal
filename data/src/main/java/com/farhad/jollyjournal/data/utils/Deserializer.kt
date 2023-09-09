package com.farhad.jollyjournal.data.utils

import com.farhad.jollyjournal.data.entity.NewsEntity
import com.farhad.jollyjournal.data.entity.NewsEntityType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object NewsDeserializer : JsonContentPolymorphicSerializer<NewsEntity>(NewsEntity::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<NewsEntity> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            NewsEntityType.ARTICLE.typeName -> NewsEntity.Article.serializer()
            NewsEntityType.VIDEO.typeName -> NewsEntity.Video.serializer()
            else -> throw Exception("Unknown Module: key 'type' not found or does not matches any module type")
        }
    }
}

object NewsEntityTypeSerializer : KSerializer<NewsEntityType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("NewsEntityType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: NewsEntityType) {
        encoder.encodeString(value.typeName)
    }

    override fun deserialize(decoder: Decoder): NewsEntityType {
        val typeName = decoder.decodeString()
        return NewsEntityType from typeName
    }
}