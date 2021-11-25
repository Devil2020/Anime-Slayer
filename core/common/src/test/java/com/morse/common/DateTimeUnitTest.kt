package com.morse.tasktemplete

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class DateTimeUnitTest {

    @Test
    fun counttext() {
        val yu = TextMethods.counttext("tuuuuu")
        assertEquals(yu >= 4 , true)
    }

    @Test
    fun capitalizeText() {
        val yu = TextMethods.capitalizeText("tuuuuu")
        assertTrue(yu.contains("TUUUUU"))
    }

}