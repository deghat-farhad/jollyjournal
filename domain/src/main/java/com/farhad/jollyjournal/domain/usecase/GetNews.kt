package com.farhad.jollyjournal.domain.usecase

import com.farhad.jollyjournal.domain.model.News
import com.farhad.jollyjournal.domain.repository.NewsContract
import com.farhad.jollyjournal.domain.usecase.base.UseCase

class GetNews(
    private val newsContract: NewsContract
) : UseCase<List<News>> {
    override suspend fun invoke(): Result<List<News>> = newsContract.getNews()
}