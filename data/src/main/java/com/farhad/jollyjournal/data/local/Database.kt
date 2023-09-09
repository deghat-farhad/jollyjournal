package com.farhad.jollyjournal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farhad.jollyjournal.data.entity.NewsRoomEntity
import com.farhad.jollyjournal.data.utils.ListStringConverter
import com.farhad.jollyjournal.data.utils.NewsRoomEntityTypeConverters

@Database(entities = [NewsRoomEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class, NewsRoomEntityTypeConverters::class)
abstract class Database : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
