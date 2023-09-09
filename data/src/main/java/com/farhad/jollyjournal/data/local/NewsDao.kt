package com.farhad.jollyjournal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.farhad.jollyjournal.data.entity.NewsRoomEntity

@Dao
interface NewsDao {
    @Insert
    suspend fun insertAll(newsEntities: List<NewsRoomEntity>)

    @Query("SELECT * FROM NewsRoomEntity")
    suspend fun getAllNews(): List<NewsRoomEntity>
}