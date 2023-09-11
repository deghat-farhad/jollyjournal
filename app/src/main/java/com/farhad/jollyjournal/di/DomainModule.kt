package com.farhad.jollyjournal.com.farhad.jollyjournal.di

import com.farhad.jollyjournal.data.repository.NewsRepository
import com.farhad.jollyjournal.domain.repository.NewsContract
import com.farhad.jollyjournal.domain.usecase.GetNews
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun newsContract(newsRepository: NewsRepository): NewsContract

    companion object {
        @Provides
        fun getNews(
            newsRepository: NewsContract
        ) = GetNews(
            newsRepository
        )
    }
}