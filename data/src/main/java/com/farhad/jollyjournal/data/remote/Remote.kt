package com.farhad.jollyjournal.data.remote

class Remote(private val serviceGenerator: ServiceGenerator) {
    suspend fun getNews(): List<String> =
        serviceGenerator.newsService().getNews()
}
