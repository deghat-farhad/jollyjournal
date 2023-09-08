package com.farhad.jollyjournal.data.di

import com.farhad.jollyjournal.data.remote.Remote
import com.farhad.jollyjournal.data.remote.ServiceGenerator
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Provides
    @Singleton
    fun remote(serviceGenerator: ServiceGenerator): Remote {
        return Remote(serviceGenerator)
    }

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