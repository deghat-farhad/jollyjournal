package com.farhad.jollyjournal.data.remote

import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.data.mapper.NewsEntityMapper
import javax.inject.Inject

class Remote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val newsEntityMapper: NewsEntityMapper,
) {
    suspend fun getNews(): List<News> =
        serviceGenerator.newsService().getNews().map { newsEntityMapper.toDomain(it) }
}
