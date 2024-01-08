package com.fedor.attendancerecording

import android.util.Log
import java.time.LocalDate

class CalendarLogic {
    fun getDayName(year: Int, month: Int, day: Int): String {
        return LocalDate.of(year, month, day).dayOfWeek.name
    }
    fun getFirstDayNum(year: Int, month: Int): Int {
        return LocalDate.of(year, month, 1).dayOfWeek.value
    }
    fun getLastDayNum(year: Int, month: Int): Int {
        return LocalDate.of(year, month, getDaysCount(year, month)).dayOfWeek.value
    }
    fun getDaysCount(year: Int, month: Int): Int {
        Log.i("CalendarViewModel", "DaysCount = ${month}/${year}")
        val daysInMonth: Array<Int> = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        return when (month == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
            true -> daysInMonth[month - 1] + 1
            false -> daysInMonth[month - 1]
        }
    }
}