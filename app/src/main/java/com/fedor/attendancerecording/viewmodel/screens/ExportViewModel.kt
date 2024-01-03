package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.model.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.viewmodel.CalendarViewModel
import java.time.LocalDate

class ExportViewModel(
    private val recordsRepository: RecordRepository
) : CalendarViewModel() {
    var dateLabelText by mutableStateOf(getGracefulDateText())

    val formats: List<String> = listOf("csv", "xlsx")

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