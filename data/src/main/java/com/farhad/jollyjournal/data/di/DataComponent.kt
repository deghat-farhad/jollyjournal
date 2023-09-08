package com.farhad.jollyjournal.data.di

import dagger.Component

@Component(modules = [DomainModule::class])
interface DataComponent {
    // Define injection functions here
}