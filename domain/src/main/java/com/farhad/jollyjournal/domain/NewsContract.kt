package com.farhad.jollyjournal.domain

import News

interface NewsContract {
    suspend fun getNews(): Result<List<News>>
}