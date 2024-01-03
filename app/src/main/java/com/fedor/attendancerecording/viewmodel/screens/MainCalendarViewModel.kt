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

class MainCalendarViewModel(
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
        return LocalDate.of(year, month, day)
    }
}