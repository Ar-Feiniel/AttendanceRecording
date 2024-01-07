package com.fedor.attendancerecording.viewmodel.screens

import com.fedor.attendancerecording.model.repositories.interfaces.ScheduleRepository
import com.fedor.attendancerecording.model.repositories.offline.OfflineSettingRepository
import com.fedor.attendancerecording.viewmodel.CalendarViewModel

public final class ScheduleViewModel(
    private val scheduleRepository: ScheduleRepository
) : CalendarViewModel() {

}