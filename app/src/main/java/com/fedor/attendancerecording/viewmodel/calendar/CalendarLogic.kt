package com.fedor.attendancerecording.viewmodel.calendar

import java.time.LocalDate

class CalendarLogic {
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

    private fun getDaysCount(year: Int, month: Int): Int {
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
}