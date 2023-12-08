package com.fedor.attendancerecording.viewmodel.screens.maincalendar

import java.time.LocalDate
import java.util.Date

class CalendarItem(val year: Int, private val _month: Int, private val _day: Int) {
    public val day: String
        get() {
            return when (_day in 1..9) {
                true -> "0${_day}"
                false -> "${_day}"
            }
        }
    public val month: String
        get() {
            return when (_month in 1..9) {
                true -> "0${_month}"
                false -> "${_month}"
            }
        }
    public val date: Date
        get(){
            return Date(year, _month, _day)
        }
    public val isWorkingDay: Boolean;
    public val isCurrent: Boolean;
    init {
        isCurrent = LocalDate.now().toString() == "${year}-${month}-${day}"
        isWorkingDay = MainCalendarViewModel.getDayName(year, _month, _day) in arrayOf<String>("SUNDAY")
    }
}