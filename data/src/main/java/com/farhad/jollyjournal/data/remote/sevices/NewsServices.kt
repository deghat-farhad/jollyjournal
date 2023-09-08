package com.farhad.jollyjournal.data.remote.sevices

import retrofit2.http.GET

interface NewsServices {
    @GET("news")
    suspend fun getNews(): List<String>
}