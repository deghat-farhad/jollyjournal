package com.farhad.jollyjournal.data.repository

import com.farhad.jollyjournal.data.cache.Cache
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.Result

class NewsRepositoryTest {

    @Mock
    private lateinit var cache: Cache

    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        newsRepository = NewsRepository(cache)
    }

    @Test
    fun testGetNews() = runBlocking {
        val dummyResult: Result<List<News>> = Result.success(listOf(dummyArticle, dummyVideo))

        `when`(cache.getNews()).thenReturn(dummyResult)

        val result = newsRepository.getNews()

        assertEquals(dummyResult, result)
    }

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