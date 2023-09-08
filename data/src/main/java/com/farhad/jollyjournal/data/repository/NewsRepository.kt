package com.farhad.jollyjournal.data.repository

import com.farhad.jollyjournal.data.remote.Remote
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remote: Remote) {

    fun act() {
        runBlocking {
            println(remote.getNews().first())
        }
    }
}