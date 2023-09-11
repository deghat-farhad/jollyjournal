package com.farhad.jollyjournal.data.remote

import com.farhad.jollyjournal.data.entity.NewsEntity
import com.farhad.jollyjournal.data.entity.NewsEntityType
import com.farhad.jollyjournal.data.mapper.NewsEntityMapper
import com.farhad.jollyjournal.data.remote.sevices.NewsServices
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RemoteTest {

    @Mock
    private lateinit var serviceGenerator: ServiceGenerator

    @Mock
    private lateinit var newsEntityMapper: NewsEntityMapper

    @Mock
    private lateinit var newsService: NewsServices

    private lateinit var remote: Remote

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remote = Remote(serviceGenerator, newsEntityMapper)
    }

    @Test
    fun testGetNews() = runBlocking {
        val dummyNewsEntity = NewsEntity.Article(
            type = NewsEntityType.ARTICLE,
            imageURL = "article_image_url",
            headline = "article_headline",
            description = "article_description",
            articleURL = "article_url",
            hashtags = listOf("tag1", "tag2"),
            isPaid = true
        )

        val dummyNews = News.Article(
            type = NewsType.ARTICLE,
            imageURL = "article_image_url",
            headline = "article_headline",
            description = "article_description",
            articleURL = "article_url",
            hashtags = listOf("tag1", "tag2"),
            isPaid = true
        )

        `when`(serviceGenerator.newsService()).thenReturn(newsService)
        `when`(newsService.getNews()).thenReturn(listOf(dummyNewsEntity))
        `when`(newsEntityMapper.toDomain(dummyNewsEntity)).thenReturn(dummyNews)

        val result = remote.getNews()

        assertEquals(listOf(dummyNews), result)
    }
}