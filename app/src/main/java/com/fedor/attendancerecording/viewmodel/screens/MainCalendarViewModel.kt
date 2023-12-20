package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.viewmodel.CalendarViewModel
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

public final class MainCalendarViewModel(
    savedStateHandle: SavedStateHandle
) : CalendarViewModel() {
    var monthListUiState by mutableStateOf(getMonthList())
    var dateLabelText by mutableStateOf(getGracefulDateText())

    fun goToPreviousMonth(){
        stepByDate("previous")
        Log.i("MainCalendarViewModel", "go_to_previous")
    }

    fun goToNextMonth(){
        stepByDate("next")
        Log.i("MainCalendarViewModel", "go_to_next")
    }

    private fun stepByDate(action: String){
        selectedDate = computeDateByOffset(action)
        monthListUiState = getMonthList()
        dateLabelText = getGracefulDateText()
    }

    private fun computeDateByOffset(action: String): LocalDate {
        val day: Int = selectedDate.dayOfMonth
        var month: Int = selectedDate.monthValue
        var year: Int = selectedDate.year
        when(action){
            "previous" -> {
                if(month -1 <=0){
                    year--
                    month = 12
                }
                else{
                    month--
                }
            }
            "next" -> {
                if(month +1 > 12){
                    year++
                    month = 1
                }
                else{
                    month++
                }
            }
        }

//
//        val yearsInOffset: Int = ceil(monthOffset.toFloat()/12).toInt()
//
//        val floatYears: Int = getYearsInMonthNum(monthOffset) + if (month + monthOffset <= 0) 1 else 0
//        val floatMonths: Int = abs(monthOffset-(floatYears*12))
//
//        Log.i("CalendarViewModel", "floatYears/floatMonths = ${floatYears}/${floatMonths}")
//
//        // to previous
//        if( monthOffset < 0 ){
//            if(month + monthOffset <= 0){
//                year -= floatYears
//                month = 12 - floatMonths
//            }
//            else{
//                month += monthOffset
//            }
//        }
//
//        // to next
//        if( monthOffset > 0){
//            if( monthOffset >= 0 ){
//                if(month + monthOffset > 12){
//                    year += floatYears
//                    month = floatMonths
//                }
//                else{
//                    month += monthOffset
//                }
//            }
//        }

        Log.i("CalendarViewModel", "computeDateByOffset = ${month}/${year}/${day}")
        return LocalDate.of(year, month, day)
    }
}