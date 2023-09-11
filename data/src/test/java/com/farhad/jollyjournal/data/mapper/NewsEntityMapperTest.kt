package com.farhad.jollyjournal.data.mapper

import com.farhad.jollyjournal.data.entity.NewsEntity
import com.farhad.jollyjournal.data.entity.NewsEntityType
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsEntityMapperTest {
    private lateinit var newsEntityMapper: NewsEntityMapper

    @Before
    fun setUp() {
        newsEntityMapper = NewsEntityMapper()
    }

    @Test
    fun testToDomainWithArticleEntity() {
        val articleEntity = NewsEntity.Article(
            NewsEntityType.ARTICLE,
            "image_url",
            "headline",
            "description",
            "article_url",
            listOf("tag1", "tag2"),
            true
        )

        val result = newsEntityMapper.toDomain(articleEntity)

        assertEquals(
            News.Article(
                NewsType.ARTICLE,
                "image_url",
                "headline",
                "description",
                "article_url",
                listOf("tag1", "tag2"),
                true
            ),
            result
        )
    }

    @Test
    fun testToDomainWithVideoEntity() {
        val videoEntity = NewsEntity.Video(
            NewsEntityType.VIDEO,
            "image_url",
            "headline",
            "video_type",
            "duration",
            "video_url",
            true
        )

        val result = newsEntityMapper.toDomain(videoEntity)

        assertEquals(
            News.Video(
                NewsType.VIDEO,
                "image_url",
                "headline",
                "video_type",
                "duration",
                "video_url",
                true
            ),
            result
        )
    }
}