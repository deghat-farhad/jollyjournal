package com.farhad.jollyjournal.data

import com.farhad.jollyjournal.data.remote.Remote
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataRepository @Inject constructor(private val remote: Remote) {

    fun act() {
        runBlocking {
            println(remote.getNews())
        }
    }
}