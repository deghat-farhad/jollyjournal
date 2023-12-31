package com.farhad.jollyjournal.data.local

import com.farhad.jollyjournal.data.mapper.NewsRoomEntityMapper
import com.farhad.jollyjournal.domain.model.News
import javax.inject.Inject

class Local @Inject constructor(
    private val newsDao: NewsDao,
    private val newsRoomEntityMapper: NewsRoomEntityMapper,
) {
    suspend fun getNews(): List<News> = newsRoomEntityMapper.toDomain(newsDao.getAllNews())

    suspend fun storeNews(newsEntityList: List<News>) {
        newsDao.deleteAndInsertAll(newsEntityList.map { newsEntity ->
            newsRoomEntityMapper.toRoom(
                newsEntity
            )
        })
    }
}