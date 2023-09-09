package com.farhad.jollyjournal.data.utils

import androidx.room.TypeConverter
import com.farhad.jollyjournal.data.entity.NewsRoomEntityType

class ListStringConverter {
    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")?.map { it.trim() }
    }
}

class NewsRoomEntityTypeConverters {
    @TypeConverter
    fun toNewsRoomEntityType(value: String) = NewsRoomEntityType.from(value)

    @TypeConverter
    fun fromNewsRoomEntityType(value: NewsRoomEntityType) = value.typeName
}

