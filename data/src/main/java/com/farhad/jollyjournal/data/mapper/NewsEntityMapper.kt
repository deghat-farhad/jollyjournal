package com.farhad.jollyjournal.data.mapper

import com.farhad.jollyjournal.data.entity.NewsEntity
import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.model.NewsType
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() {
    fun toDomain(newsEntity: NewsEntity): News = when (newsEntity) {
        is NewsEntity.Article -> {
            News.Article(
                NewsType from newsEntity.type.typeName,
                newsEntity.imageURL,
                newsEntity.headline,
                newsEntity.description,
                newsEntity.articleURL,
                newsEntity.hashtags,
                newsEntity.isPaid,
            )
        }

        is NewsEntity.Video -> {
            News.Video(
                NewsType from newsEntity.type.typeName,
                newsEntity.imageURL,
                newsEntity.headline,
                newsEntity.videoType,
                newsEntity.duration,
                newsEntity.videoURL,
                newsEntity.isPaid,
            )
        }
    }
}