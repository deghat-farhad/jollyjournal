package com.farhad.jollyjournal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.farhad.jollyjournal.data.entity.NewsRoomEntity

@Dao
interface NewsDao {
    @Transaction
    suspend fun deleteAndInsertAll(users: List<NewsRoomEntity>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    suspend fun insertAll(newsEntities: List<NewsRoomEntity>)

    @Query("SELECT * FROM NewsRoomEntity")
    suspend fun getAllNews(): List<NewsRoomEntity>

    @Query("DELETE FROM NewsRoomEntity")
    suspend fun deleteAll()
}