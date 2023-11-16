package com.fedor.attendancerecording.viewmodel.mainpage

class CalendarDate(val _year: Int, val _month: Int, val _day: Int) {
    private val year: Int;
    private val month: Int;
    private val day: Int;
    private val type: DateTypes = DateTypes.WORKDAY;
    init {
        year=_year;
        month=_month;
        day=_day;
    }
}