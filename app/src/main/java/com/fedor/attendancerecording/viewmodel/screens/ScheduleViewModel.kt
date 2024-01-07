package com.fedor.attendancerecording.viewmodel.screens

import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import com.fedor.attendancerecording.viewmodel.CalendarViewModel

class ScheduleViewModel(
    private val scheduleRepository: ScheduleRepository
) : CalendarViewModel() {

}