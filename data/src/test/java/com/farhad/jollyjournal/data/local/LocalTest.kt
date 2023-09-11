package com.farhad.jollyjournal.data.local

import com.farhad.jollyjournal.data.entity.NewsRoomEntity
import com.farhad.jollyjournal.data.entity.NewsRoomEntityType
import com.farhad.jollyjournal.data.mapper.NewsRoomEntityMapper
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LocalTest {

    @Mock
    private lateinit var newsDao: NewsDao

    @Mock
    private lateinit var newsRoomEntityMapper: NewsRoomEntityMapper

    private lateinit var local: Local

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        local = Local(newsDao, newsRoomEntityMapper)
    }

    @Test
    fun testGetNews() = runTest {
        val mockRoomEntities = listOf(dummyArticleRoomEntity, dummyVideoRoomEntity)
        `when`(newsDao.getAllNews()).thenReturn(mockRoomEntities)

        val mockNews = listOf(dummyArticle, dummyVideo)
        `when`(newsRoomEntityMapper.toDomain(mockRoomEntities)).thenReturn(mockNews)

        val result = local.getNews()

        assertEquals(mockNews, result)
    }

    @Test
    fun testStoreNews() = runTest {
        val mockNews = listOf(dummyArticle, dummyVideo)
        val mockRoomEntities = listOf(dummyArticleRoomEntity, dummyVideoRoomEntity)

        `when`(newsRoomEntityMapper.toRoom(dummyArticle)).thenReturn(dummyArticleRoomEntity)
        `when`(newsRoomEntityMapper.toRoom(dummyVideo)).thenReturn(dummyVideoRoomEntity)

        local.storeNews(mockNews)

        verify(newsDao).deleteAndInsertAll(mockRoomEntities)
    }

    private val dummyArticleRoomEntity = NewsRoomEntity(
        type = NewsRoomEntityType.ARTICLE,
        imageURL = "article_image_url",
        headline = "article_headline",
        isPaid = true,
        description = "article_description",
        articleURL = "article_url",
        hashtags = listOf("tag1", "tag2"),
        videoType = null,
        duration = null,
        videoURL = null
    )

    private val dummyVideoRoomEntity = NewsRoomEntity(
        type = NewsRoomEntityType.VIDEO,
        imageURL = "video_image_url",
        headline = "video_headline",
        isPaid = true,
        description = null,
        articleURL = null,
        hashtags = null,
        videoType = "video_type",
        duration = "video_duration",
        videoURL = "video_url"
    )

    private val dummyArticle = News.Article(
        NewsType.ARTICLE,
        "articleImageURL",
        "Article Headline",
        "Article Description",
        "articleURL",
        listOf("tag1", "tag2"),
        true
    )

    private val dummyVideo = News.Video(
        NewsType.VIDEO,
        "videoImageURL",
        "Video Headline",
        "MP4",
        "10:00",
        "videoURL",
        false
    )
}