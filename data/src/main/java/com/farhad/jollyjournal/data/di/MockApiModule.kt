package com.farhad.jollyjournal.data.di

import android.content.Context
import com.farhad.jollyjournal.data.R
import com.farhad.jollyjournal.data.utils.MockNetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import java.util.Scanner

@Module
@InstallIn(SingletonComponent::class)
class MockApiModule {
    @Provides
    fun getMockInterceptor(
        mockInterceptor: MockNetworkInterceptor,
        @ApplicationContext application: Context
    ): Interceptor {
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