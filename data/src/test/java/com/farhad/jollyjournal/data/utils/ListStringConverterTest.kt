package com.farhad.jollyjournal.data.utils

import com.farhad.jollyjournal.data.entity.NewsRoomEntityType
import org.junit.Assert.assertEquals
import org.junit.Test

class ConvertersTest {

    // Test ListStringConverter

    @Test
    fun testToStringConversion() {
        val converter = ListStringConverter()

        val inputList = listOf("item1", "item2", "item3")
        val expectedString = "item1,item2,item3"

        val result = converter.toString(inputList)

        assertEquals(expectedString, result)
    }

    @Test
    fun testFromStringConversion() {
        val converter = ListStringConverter()

        val inputString = "item1,item2,item3"
        val expectedList = listOf("item1", "item2", "item3")

        val result = converter.fromString(inputString)

        assertEquals(expectedList, result)
    }

    // Test NewsRoomEntityTypeConverters

    @Test
    fun testToNewsRoomEntityTypeConversion() {
        val converter = NewsRoomEntityTypeConverters()

        val inputString = "Article"
        val expectedType = NewsRoomEntityType.ARTICLE

        val result = converter.toNewsRoomEntityType(inputString)

        assertEquals(expectedType, result)
    }

    @Test
    fun testFromNewsRoomEntityTypeConversion() {
        val converter = NewsRoomEntityTypeConverters()

        val inputType = NewsRoomEntityType.ARTICLE
        val expectedString = "Article"

        val result = converter.fromNewsRoomEntityType(inputType)

        assertEquals(expectedString, result)
    }
}