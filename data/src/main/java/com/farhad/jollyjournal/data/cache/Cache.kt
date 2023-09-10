package com.farhad.jollyjournal.data.cache

import com.farhad.jollyjournal.domain.model.News
import android.util.Log
import com.farhad.jollyjournal.data.local.Local
import com.farhad.jollyjournal.data.remote.Remote
import javax.inject.Inject

class Cache @Inject constructor(
    private val remote: Remote,
    private val local: Local,
) {
    companion object {
        private const val TAG = "Cache"
    }

    suspend fun getNews(): Result<List<News>> {
        return try {
            val newsList = try {
                remote.getNews().also { newsList ->
                    local.storeNews(newsList)
                }
            } catch (e: Exception) {
                Log.w(TAG, "API call failed, fall back to local cache: $e")
                local.getNews()
            }
            Result.success(newsList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}