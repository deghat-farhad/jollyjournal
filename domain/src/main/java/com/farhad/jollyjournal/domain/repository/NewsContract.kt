package com.farhad.jollyjournal.domain.repository

import com.farhad.jollyjournal.domain.model.News

interface NewsContract {
    suspend fun getNews(): Result<List<News>>
}