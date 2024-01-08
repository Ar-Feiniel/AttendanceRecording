package com.fedor.attendancerecording.viewmodel.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.data.entity.ScheduleDay
import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import com.fedor.attendancerecording.viewmodel.CalendarViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainCalendarViewModel(
    private val scheduleRepository: ScheduleRepository
) : CalendarViewModel() {
    private val _uiState: MutableStateFlow<MainCalendarScreenUiState> = MutableStateFlow(MainCalendarScreenUiState())
    val uiState: StateFlow<MainCalendarScreenUiState> = _uiState.asStateFlow()
    data class MainCalendarScreenUiState(
        val scheduleRecordsList: List<ScheduleDay> = listOf<ScheduleDay>()
    )
    init {
        refreshUiState()
    }
    private fun refreshUiState(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    scheduleRecordsList = scheduleRepository.getListByMonth(selectedDate.monthValue, selectedDate.year)
                )
            }
        }
    }

    var dateLabelText by mutableStateOf(getGracefulSelectedMonthYearText())

    fun goToPreviousMonth(){
        stepByDate("previous")
    }

    fun goToNextMonth(){
        stepByDate("next")
    }

    private fun stepByDate(action: String){
        selectedDate = computeDateByOffset(action)
        dateLabelText = getGracefulSelectedMonthYearText()
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