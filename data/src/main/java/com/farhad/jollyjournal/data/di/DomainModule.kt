package com.farhad.jollyjournal.data.di

import com.farhad.jollyjournal.domain.MyClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideMyClass(): MyClass {
        return MyClass()
    }
}