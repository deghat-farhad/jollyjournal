package com.farhad.jollyjournal.data.cache

import com.farhad.jollyjournal.data.local.Local
import com.farhad.jollyjournal.data.remote.Remote
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.anyList
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class CacheTest {
    @Test
    fun testGetNewsSuccess() = runTest {
        val mockRemote = mock(Remote::class.java)
        val mockLocal = mock(Local::class.java)

        val cache = Cache(mockRemote, mockLocal)

        val expectedResult = Result.success(listOf<News>())

        `when`(mockRemote.getNews()).thenReturn(expectedResult.getOrNull())

        val result = cache.getNews()

        verify(mockRemote, times(1)).getNews()
        verify(mockLocal, times(1)).storeNews(expectedResult.getOrThrow())

        assertEquals(expectedResult, result)
    }

    @Test()
    fun testGetNewsFallbackToLocal() = runTest {
        val mockRemote = mock(Remote::class.java)
        val mockLocal = mock(Local::class.java)

        val cache = Cache(mockRemote, mockLocal)

        `when`(mockRemote.getNews()).thenThrow(RuntimeException::class.java)

        val cachedNews = listOf(dummyArticle, dummyVideo)
        `when`(mockLocal.getNews()).thenReturn(cachedNews)

        val result = cache.getNews()

        verify(mockRemote, times(1)).getNews()

        verify(mockLocal, times(0)).storeNews(anyList())
        verify(mockLocal, times(1)).getNews()

        assertEquals(Result.success(cachedNews), result)
    }

    @Test()
    fun testGetNewsFallbackToLocalWithEmptyCache() = runTest {
        val mockRemote = mock(Remote::class.java)
        val mockLocal = mock(Local::class.java)

        val cache = Cache(mockRemote, mockLocal)

        val throwable = RuntimeException("Remote data source failed")

        `when`(mockRemote.getNews()).thenThrow(throwable)

        val cachedNews = emptyList<News>()
        `when`(mockLocal.getNews()).thenReturn(cachedNews)

        val result = cache.getNews()

        `when`(mockLocal.getNews()).thenReturn(cachedNews)

        `when`(mockLocal.storeNews(emptyList())).thenReturn(Unit)

        val expectedFailure = Result.failure<List<News>>(throwable)
        assertEquals(expectedFailure, result)
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