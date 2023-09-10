package com.farhad.jollyjournal.data.repository

import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.data.cache.Cache
import com.farhad.jollyjournal.domain.repository.NewsContract
import javax.inject.Inject

class NewsRepository @Inject constructor(private val cache: Cache) : NewsContract {
    override suspend fun getNews(): Result<List<News>> {
        return cache.getNews()
    }
}