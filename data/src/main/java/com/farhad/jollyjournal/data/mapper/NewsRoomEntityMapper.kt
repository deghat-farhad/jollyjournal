package com.farhad.jollyjournal.data.mapper

import News
import NewsType
import com.farhad.jollyjournal.data.entity.NewsRoomEntity
import com.farhad.jollyjournal.data.entity.NewsRoomEntityType
import javax.inject.Inject

class NewsRoomEntityMapper @Inject constructor() {
    fun toRoom(news: News): NewsRoomEntity = when (news) {
        is News.Article -> {
            NewsRoomEntity(
                type = NewsRoomEntityType from news.type.typeName,
                imageURL = news.imageURL,
                headline = news.headline,
                isPaid = news.isPaid,
                description = news.description,
                articleURL = news.articleURL,
                hashtags = news.hashtags,
                videoType = null,
                duration = null,
                videoURL = null,
            )
        }

        is News.Video -> {
            NewsRoomEntity(
                type = NewsRoomEntityType from news.type.typeName,
                imageURL = news.imageURL,
                headline = news.headline,
                isPaid = news.isPaid,
                description = null,
                articleURL = null,
                hashtags = null,
                videoType = news.videoType,
                duration = news.duration,
                videoURL = news.videoURL,
            )
        }
    }

    fun toDomain(newsRoomEntity: NewsRoomEntity): News = when (newsRoomEntity.type) {
        NewsRoomEntityType.ARTICLE -> {
            News.Article(
                NewsType from newsRoomEntity.type.typeName,
                newsRoomEntity.imageURL,
                newsRoomEntity.headline,
                newsRoomEntity.description ?: throw IllegalArgumentException(),
                newsRoomEntity.articleURL ?: throw IllegalArgumentException(),
                newsRoomEntity.hashtags ?: throw IllegalArgumentException(),
                newsRoomEntity.isPaid
            )
        }

        NewsRoomEntityType.VIDEO -> {
            News.Video(
                NewsType from newsRoomEntity.type.typeName,
                newsRoomEntity.imageURL,
                newsRoomEntity.headline,
                newsRoomEntity.videoType ?: throw IllegalArgumentException(),
                newsRoomEntity.duration ?: throw IllegalArgumentException(),
                newsRoomEntity.videoURL ?: throw IllegalArgumentException(),
                newsRoomEntity.isPaid
            )
        }
    }

    fun toDomain(newsRoomEntityList: List<NewsRoomEntity>): List<News> =
        newsRoomEntityList.map { newsRoomEntity -> toDomain(newsRoomEntity) }
}