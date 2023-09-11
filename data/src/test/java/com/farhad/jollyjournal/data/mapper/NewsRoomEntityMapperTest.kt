package com.farhad.jollyjournal.data.mapper

import com.farhad.jollyjournal.data.entity.NewsRoomEntity
import com.farhad.jollyjournal.data.entity.NewsRoomEntityType
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class NewsRoomEntityMapperTest {
    private lateinit var newsRoomEntityMapper: NewsRoomEntityMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        newsRoomEntityMapper = NewsRoomEntityMapper()
    }

    @Test
    fun testToRoomWithArticleNews() {
        val articleNews = News.Article(
            NewsType.ARTICLE,
            "image_url",
            "headline",
            "description",
            "article_url",
            listOf("tag1", "tag2"),
            true
        )

        val result = newsRoomEntityMapper.toRoom(articleNews)

        assertEquals(
            NewsRoomEntity(
                type = NewsRoomEntityType.ARTICLE,
                imageURL = "image_url",
                headline = "headline",
                isPaid = true,
                description = "description",
                articleURL = "article_url",
                hashtags = listOf("tag1", "tag2"),
                videoType = null,
                duration = null,
                videoURL = null
            ),
            result
        )
    }

    @Test
    fun testToRoomWithVideoNews() {
        val videoNews = News.Video(
            NewsType.VIDEO,
            "image_url",
            "headline",
            "video_type",
            "duration",
            "video_url",
            true
        )

        val result = newsRoomEntityMapper.toRoom(videoNews)

        assertEquals(
            NewsRoomEntity(
                type = NewsRoomEntityType.VIDEO,
                imageURL = "image_url",
                headline = "headline",
                isPaid = true,
                description = null,
                articleURL = null,
                hashtags = null,
                videoType = "video_type",
                duration = "duration",
                videoURL = "video_url"
            ),
            result
        )
    }

    @Test
    fun testToDomainWithArticleRoomEntity() {
        val articleRoomEntity = NewsRoomEntity(
            type = NewsRoomEntityType.ARTICLE,
            imageURL = "image_url",
            headline = "headline",
            isPaid = true,
            description = "description",
            articleURL = "article_url",
            hashtags = listOf("tag1", "tag2"),
            videoType = null,
            duration = null,
            videoURL = null
        )

        val result = newsRoomEntityMapper.toDomain(articleRoomEntity)

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
    fun testToDomainWithVideoRoomEntity() {
        val videoRoomEntity = NewsRoomEntity(
            type = NewsRoomEntityType.VIDEO,
            imageURL = "image_url",
            headline = "headline",
            isPaid = true,
            description = null,
            articleURL = null,
            hashtags = null,
            videoType = "video_type",
            duration = "duration",
            videoURL = "video_url"
        )

        val result = newsRoomEntityMapper.toDomain(videoRoomEntity)

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

    @Test
    fun testToDomainList() {
        val roomEntities = listOf(
            NewsRoomEntity(
                type = NewsRoomEntityType.ARTICLE,
                imageURL = "image_url1",
                headline = "headline1",
                isPaid = true,
                description = "description1",
                articleURL = "article_url1",
                hashtags = listOf("tag1", "tag2"),
                videoType = null,
                duration = null,
                videoURL = null
            ),
            NewsRoomEntity(
                type = NewsRoomEntityType.VIDEO,
                imageURL = "image_url2",
                headline = "headline2",
                isPaid = true,
                description = null,
                articleURL = null,
                hashtags = null,
                videoType = "video_type2",
                duration = "duration2",
                videoURL = "video_url2"
            )
        )

        val result = newsRoomEntityMapper.toDomain(roomEntities)

        val expectedNews = listOf(
            News.Article(
                NewsType.ARTICLE,
                "image_url1",
                "headline1",
                "description1",
                "article_url1",
                listOf("tag1", "tag2"),
                true
            ),
            News.Video(
                NewsType.VIDEO,
                "image_url2",
                "headline2",
                "video_type2",
                "duration2",
                "video_url2",
                true
            )
        )
        assertEquals(expectedNews, result)
    }
}
