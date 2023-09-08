package com.farhad.jollyjournal.data.di

import android.content.Context
import com.farhad.jollyjournal.data.MockNetworkInterceptor
import com.farhad.jollyjournal.data.R
import com.farhad.jollyjournal.data.remote.Remote
import com.farhad.jollyjournal.data.remote.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Scanner
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
        @Named("BaseUrl") baseUrl: String
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Named("BaseUrl")
    fun baseUrl() = "http://localhost/"

    @Provides
    fun httpClient(
        mockInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(mockInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    @Provides
    fun getMockInterceptor(
        mockInterceptor: MockNetworkInterceptor,
        @ApplicationContext application: Context
        ): Interceptor{
        return mockInterceptor.mock(
                "http://localhost/news",
                {
                    val inputStream = application.resources.openRawResource(R.raw.data)
                    val scanner = Scanner(inputStream, "UTF-8")
                    val stringBuilder = StringBuilder()

                    scanner.use {
                        while (it.hasNextLine()) {
                            stringBuilder.append(it.nextLine())
                        }
                    }
                    stringBuilder.toString()
                },
                200,
                1500
            )
    }
}