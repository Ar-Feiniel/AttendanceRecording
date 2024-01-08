package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.entity.ScheduleDay
import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import com.fedor.attendancerecording.viewmodel.CalendarViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleCalendarViewModel(
    private val scheduleRepository: ScheduleRepository
) : CalendarViewModel() {
    val scheduleDayTypes: Map<Boolean, Int> =
        mapOf(
            true to R.string.weekday,
            false to R.string.holiday
        )

    private val _uiState: MutableStateFlow<ScheduleScreenUiState> = MutableStateFlow(ScheduleScreenUiState())
    val uiState: StateFlow<ScheduleScreenUiState> = _uiState.asStateFlow()

    init {
        refreshUiState()
    }

    data class ScheduleScreenUiState(
        val scheduleRecordsList: List<ScheduleDay> = listOf<ScheduleDay>()
    )

    fun upsertSchedule(schedule: ScheduleDay){
        viewModelScope.launch {
            scheduleRepository.upsertItem(schedule)
        }
        refreshUiState()
    }

    fun refreshUiState(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    scheduleRecordsList = scheduleRepository.getListByMonth(selectedDate.monthValue, selectedDate.year)
                )
            }
        }
    }
}