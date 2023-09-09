package com.farhad.jollyjournal.data.local

import News
import com.farhad.jollyjournal.data.mapper.NewsRoomEntityMapper
import javax.inject.Inject

class Local @Inject constructor(
    private val newsDao: NewsDao,
    private val newsRoomEntityMapper: NewsRoomEntityMapper,
) {
    suspend fun getNews(): List<News> = newsRoomEntityMapper.toDomain(newsDao.getAllNews())

    suspend fun storeNews(newsEntityList: List<News>) {
        newsDao.insertAll(newsEntityList.map { newsEntity -> newsRoomEntityMapper.toRoom(newsEntity) })
    }
}