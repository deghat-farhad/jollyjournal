package com.farhad.jollyjournal.data.di

import android.content.Context
import androidx.room.Room
import com.farhad.jollyjournal.data.local.Database
import com.farhad.jollyjournal.data.local.NewsDao
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Provides
    fun database(
        @ApplicationContext context: Context
    ): Database =
        Room.databaseBuilder(context, Database::class.java, "news-database").build()

    @Provides
    fun newsDao(
        database: Database
    ): NewsDao =
        database.newsDao()

    @Provides
    fun retrofit(
        httpClient: OkHttpClient,
        @Named("BaseUrl") baseUrl: String,
    ): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }

    @Provides
    @Named("BaseUrl")
    fun baseUrl() = "http://localhost/"

    @Provides
    fun httpClient(
        mockInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(mockInterceptor)
            .build()
    }
}