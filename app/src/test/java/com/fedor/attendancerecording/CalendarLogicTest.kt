package com.fedor.attendancerecording

import org.junit.Test

class CalendarLogicTest {

    private var calendarLogic: CalendarLogic = CalendarLogic()

    @Test
    fun getDayName() {
        val expected = "FRIDAY"
        assert(calendarLogic.getDayName(year = 2023, month = 12, day = 1) == expected)
    }

    @Test
    fun getFirstDayNum() {
        assert(true)
    }

    @Test
    fun getLastDayNum() {
        assert(true)
    }

    @Test
    fun getDaysCount() {
        assert(true)
    }
}