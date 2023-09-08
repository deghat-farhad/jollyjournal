package com.farhad.jollyjournal.data.di

import dagger.Component

@Component(modules = [RepositoriesModule::class, MockApiModule::class])
interface DataComponent {

}