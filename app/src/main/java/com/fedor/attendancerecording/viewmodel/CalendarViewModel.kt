package com.fedor.attendancerecording.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.util.Date
import kotlin.math.abs
import kotlin.math.ceil

public abstract class CalendarViewModel : ViewModel() {
    private var _currentDate: LocalDate = LocalDate.now()
    public var selectedDate: LocalDate = _currentDate
    public var calendarList: List<CalendarItem?> = getMonthList(selectedDate)
    public var monthList: Flow<List<CalendarItem?>> = flowOf(getMonthList())
    public fun getMonthArray(year: Int, month: Int): Array<Array<CalendarItem?>> {
        val calendar: Array<Array<CalendarItem?>> = Array(getWeeksCount(year, month)) { Array(7) { null } }
        var weeksCounter: Int = 0;
        var daysCounter: Int = 1;
        while(weeksCounter < getWeeksCount(year, month)){
            if(weeksCounter == 0){
                for(i in getFirstDayNum(year, month)-1..6){
                    calendar[0][i] = CalendarItem(year, month, daysCounter);
                    daysCounter++;
                }
            }
            else if(weeksCounter == calendar.size-1){
                for(i in 0..getLastDayNum(year, month)-1){
                    calendar[calendar.size-1][i] = CalendarItem(year, month, daysCounter);
                    daysCounter++;
                }
            }
            else{
                for(i in 0..6){
                    calendar[weeksCounter][i] = CalendarItem(year, month, daysCounter);
                    daysCounter++;
                }
            }
            weeksCounter++;
        }
        return calendar
    }
    public fun getMonthList(year: Int, month: Int): List<CalendarItem?> {
        val calendar: MutableList<CalendarItem?> = MutableList(getWeeksCount(year, month)*7, { null })
        val firstDayIndex: Int = getFirstDayNum(year, month)-1
        for (dayNum in 1..getDaysCount(year, month)){
            calendar[dayNum-1+firstDayIndex] = CalendarItem(year, month, dayNum)
        }
        return calendar
    }
    private fun getYearsInMonthNum(monthNum: Int) : Int{
        var yearCount: Int = 0
        if(monthNum > 0){
            while (true){
                if(monthNum - 12 > 0){
                    monthNum - 12
                    yearCount++
                }
                else{
                    break
                }
            }
        }
        if(monthNum < 0){
            while (true){
                if(monthNum + 12 < 0){
                    monthNum + 12
                    yearCount++
                }
                else{
                    break
                }
            }
        }
        return yearCount
    }
    public fun getMonthList(date: Date): List<CalendarItem?> {
        return getMonthList(date.year, date.month)
    }
    public fun getMonthList(localDate: LocalDate): List<CalendarItem?> {
        return getMonthList(localDate.year, localDate.monthValue)
    }
    public fun getMonthList(): List<CalendarItem?> {
        return getMonthList(selectedDate.year, selectedDate.month.value)
    }
    public fun getGracefulDateText(): String {
        //return "${_currentDate.dayOfMonth} ${_currentDate.month.name} ${_currentDate.year}"
        val month: String = when (selectedDate.month.value in 1..9) {
            true -> "0${selectedDate.month.value}"
            false -> "${selectedDate.month.value}"
        }
        // TODO: Вынести в отдельную функцию форматирвоания (и из класса с кнопками)
        return "${month}.${selectedDate.year}"
    }
    private fun getDaysCount(year: Int, month: Int): Int {
        Log.i("CalendarViewModel", "DaysCount = ${month}/${year}")
        val daysInMonth: Array<Int> = arrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        return when (month == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
            true -> daysInMonth[month - 1] + 1
            false -> daysInMonth[month - 1]
        }
    }
    private fun getWeeksCount(year: Int, month: Int): Int {
        val weeks: Array<Array<String>> = Array(6) { Array(2) { "" } }
        var currentWeek: Int = 0;
        var currentDayName: String = ""
        for(day in 1..getDaysCount(year, month)){
            currentDayName = getDayName(year, month, day)
            if(currentDayName == DaysOfWeek.MONDAY.name
                || currentDayName == DaysOfWeek.SUNDAY.name){
                if(currentDayName == DaysOfWeek.MONDAY.name && !weeks[currentWeek].contains(
                        DaysOfWeek.MONDAY.name)) weeks[currentWeek][0] = DaysOfWeek.MONDAY.name
                if(currentDayName == DaysOfWeek.SUNDAY.name && !weeks[currentWeek].contains(
                        DaysOfWeek.SUNDAY.name)) weeks[currentWeek][1] = DaysOfWeek.SUNDAY.name
                if(weeks[currentWeek].contains(DaysOfWeek.SUNDAY.name)) currentWeek++
            }
        }
        var weeksCount: Int = 0
        for(i in weeks){
            if(i[0] != "" || i[1] != "") weeksCount++
        }
        return weeksCount
    }
    companion object{
        public fun getDayName(year: Int, month: Int, day: Int): String {
            return LocalDate.of(year, month, day).dayOfWeek.name
        }
    }
    private fun getFirstDayNum(year: Int, month: Int): Int {
        return LocalDate.of(year, month, 1).dayOfWeek.value
    }
    private fun getLastDayNum(year: Int, month: Int): Int {
        return LocalDate.of(year, month, getDaysCount(year, month)).dayOfWeek.value
    }
    private fun updateDateNow(){
        _currentDate = LocalDate.now()
    }
}
enum class DaysOfWeek() {
    MONDAY(),
    TUESDAY(),
    WEDNESDAY(),
    THURSDAY(),
    FRIDAY(),
    SATURDAY(),
    SUNDAY()
}

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
        isWorkingDay = CalendarViewModel.getDayName(year, _month, _day) in arrayOf<String>("SUNDAY")
    }
}