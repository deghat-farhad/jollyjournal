package com.farhad.jollyjournal.data.utils

import com.farhad.jollyjournal.data.entity.NewsEntity
import com.farhad.jollyjournal.data.entity.NewsEntityType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class SerializationTest {

    // Test NewsDeserializer

    @Test
    fun testNewsDeserializerArticleSerialization() {
        val articleJson = """
            {
                "type": "Article",
                "imageURL": "article_image_url",
                "headline": "Headline",
                "description": "Article Description",
                "articleURL": "article_url",
                "hashtags": ["tag1", "tag2"],
                "isPaid": true
            }
        """

        val result = Json.decodeFromString<NewsEntity>(articleJson)

        val expected = NewsEntity.Article(
            NewsEntityType.ARTICLE,
            "article_image_url",
            "Headline",
            "Article Description",
            "article_url",
            listOf("tag1", "tag2"),
            true
        )

        assertEquals(expected, result)
    }

    @Test
    fun testNewsDeserializerVideoSerialization() {
        val videoJson = """
            {
                "type": "Video",
                "imageURL": "video_image_url",
                "headline": "Video Headline",
                "videoType": "Some Type",
                "duration": "5 mins",
                "videoURL": "video_url",
                "isPaid": false
            }
        """

        val result = Json.decodeFromString<NewsEntity>(videoJson)

        val expected = NewsEntity.Video(
            NewsEntityType.VIDEO,
            "video_image_url",
            "Video Headline",
            "Some Type",
            "5 mins",
            "video_url",
            false
        )

        assertEquals(expected, result)
    }

    // Test NewsEntityTypeSerializer

    @Test
    fun testNewsEntityTypeSerializerSerialize() {
        val articleType = NewsEntityType.ARTICLE

        val result = Json.encodeToString(articleType)

        assertEquals("\"Article\"", result)
    }

    @Test
    fun testNewsEntityTypeSerializerDeserialize() {
        val serializedType = "\"Video\""

        val result = Json.decodeFromString<NewsEntityType>(serializedType)

        assertEquals(NewsEntityType.VIDEO, result)
    }
}
