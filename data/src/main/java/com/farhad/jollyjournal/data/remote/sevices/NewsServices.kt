package com.farhad.jollyjournal.data.remote.sevices

import com.farhad.jollyjournal.data.entity.NewsEntity
import retrofit2.http.GET

interface NewsServices {
    @GET("news")
    suspend fun getNews(): List<NewsEntity>
}