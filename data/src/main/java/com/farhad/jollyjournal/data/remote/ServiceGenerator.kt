package com.farhad.jollyjournal.data.remote

import com.farhad.jollyjournal.data.remote.sevices.NewsServices
import retrofit2.Retrofit
import javax.inject.Inject

class ServiceGenerator @Inject constructor(private val retrofit: Retrofit) {
    fun newsService(): NewsServices = retrofit.create(NewsServices::class.java)
}
