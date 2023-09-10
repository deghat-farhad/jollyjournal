package com.farhad.jollyjournal.com.farhad.jollyjournal.di

import dagger.Component
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Component(modules = [DomainModule::class])
interface AppComponent