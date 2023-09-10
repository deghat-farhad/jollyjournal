package com.farhad.jollyjournal.com.farhad.jollyjournal.mapper

import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItemType
import com.farhad.jollyjournal.domain.model.News

class NewsItemMapper {
    fun formDomain(news: News) = when (news){
        is News.Article -> NewsItem.Article(
            NewsItemType from news.type.typeName,
            news.imageURL,
            news.headline,
            news.description,
            news.articleURL,
            news.hashtags,
            news.isPaid,
        )
        is News.Video -> NewsItem.Video(
            NewsItemType from news.type.typeName,
            news.imageURL,
            news.headline,
            news.videoType,
            news.duration,
            news.videoURL,
            news.isPaid,
        )
    }
}