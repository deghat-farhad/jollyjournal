package com.farhad.jollyjournal.data.remote

import com.farhad.jollyjournal.data.entity.NewsEntity
import com.farhad.jollyjournal.data.utils.safeApiCall
import kotlinx.coroutines.flow.Flow

class Remote(private val serviceGenerator: ServiceGenerator) {
    suspend fun getNews(): Flow<Result<List<NewsEntity>>> = safeApiCall {
        serviceGenerator.newsService().getNews()
    }
}
