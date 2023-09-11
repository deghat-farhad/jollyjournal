package com.farhad.jollyjournal.mapper

import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItemType
import com.farhad.jollyjournal.com.farhad.jollyjournal.mapper.NewsItemMapper
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class NewsItemMapperTest {

    private lateinit var newsItemMapper: NewsItemMapper

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsItemMapper = NewsItemMapper()
    }

    @Test
    fun toPresentationArticleTest() {
        val article = News.Article(
            NewsType.ARTICLE,
            "imageURL",
            "headline",
            "description",
            "articleURL",
            listOf("hashtag1", "hashtag2"),
            true
        )

        val expectedNewsItem = NewsItem.Article(
            NewsItemType.ARTICLE,
            "imageURL",
            "headline",
            "description",
            "articleURL",
            listOf("hashtag1", "hashtag2"),
            true
        )

        val result = newsItemMapper.toPresentation(article)

        assertEquals(expectedNewsItem, result)
    }

    @Test
    fun toPresentationVideoTest() {
        // Arrange
        val video = News.Video(
            NewsType.VIDEO,
            "imageURL",
            "headline",
            "videoType",
            "duration",
            "videoURL",
            false
        )

        val expectedNewsItem = NewsItem.Video(
            NewsItemType.VIDEO,
            "imageURL",
            "headline",
            "videoType",
            "duration",
            "videoURL",
            false
        )

        val result = newsItemMapper.toPresentation(video)

        assertEquals(expectedNewsItem, result)
    }

    @Test
    fun toPresentationListTest() {
        val newsList = listOf(
            News.Article(
                NewsType.ARTICLE,
                "imageURL1",
                "headline1",
                "description1",
                "articleURL1",
                listOf("hashtag1", "hashtag2"),
                true
            ),
            News.Video(
                NewsType.VIDEO,
                "imageURL2",
                "headline2",
                "videoType",
                "duration",
                "videoURL",
                false
            )
        )

        val expectedNewsItemList = listOf(
            NewsItem.Article(
                NewsItemType.ARTICLE,
                "imageURL1",
                "headline1",
                "description1",
                "articleURL1",
                listOf("hashtag1", "hashtag2"),
                true
            ),
            NewsItem.Video(
                NewsItemType.VIDEO,
                "imageURL2",
                "headline2",
                "videoType",
                "duration",
                "videoURL",
                false
            )
        )

        val result = newsItemMapper.toPresentation(newsList)

        assertEquals(expectedNewsItemList, result)
    }
}